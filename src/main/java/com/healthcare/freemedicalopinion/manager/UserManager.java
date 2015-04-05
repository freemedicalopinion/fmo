package com.healthcare.freemedicalopinion.manager;

import java.net.URLEncoder;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.manageEvent.eventGenertor.UserCreatedEventGenertor;
import com.healthcare.freemedicalopinion.security.CustomUserDetails;
import com.healthcare.freemedicalopinion.service.DoctorService;
import com.healthcare.freemedicalopinion.service.HospitalService;
import com.healthcare.freemedicalopinion.service.UserProfileService;
import com.healthcare.freemedicalopinion.service.UserService;
import com.healthcare.freemedicalopinion.utility.AppConstants;
import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles;
import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Role;
import com.healthcare.freemedicalopinion.utility.OnewayHash;
import com.healthcare.freemedicalopinion.utility.RandomPasswordGen;
import com.healthcare.freemedicalopinion.utility.ReadValueFromMessageSource;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.ChangePasswordVO;
import com.healthcare.freemedicalopinion.valueobject.DoctorProfileVO;
import com.healthcare.freemedicalopinion.valueobject.HospitalProfileVO;
import com.healthcare.freemedicalopinion.valueobject.LoginCheckResponseVO;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserVO;

@Component
public class UserManager {

	@Autowired
	UserService userService;
	@Autowired
	ReadValueFromMessageSource messageSource;
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	DoctorService doctorService;
	@Autowired
	HospitalService hospitalService;

	public UserVO getUserbyUsername(String username) {

		UserVO user = null;

		if (username != null) {
			user = userService.getUserByUserName(username);
		}

		return user;
	}

	public CustomUserDetails getUserDetailForLogin(String username) {

		if (username != null) {
			CustomUserDetails userDetail = userService
					.getUserDetailsForLogin(username);
			return userDetail;

		}

		return null;
	}

	public void createNewUser(UserProfileVO userVO) throws Exception {

		if (userVO != null) {
			try {
				UserVO vo = new UserVO();
				vo.setUsername(userVO.getUsername());
				String otp = RandomPasswordGen.randompassword();
				vo.setPassword(OnewayHash.generateStorngPasswordHash(otp));
				userService.addUser(userVO, vo);
				vo.setPassword(otp);
				new UserCreatedEventGenertor().fireEvent(vo);
			} catch (Exception e) {
				throw new Exception(

				messageSource.readValueByKey("registration.emailExist"));
			}
		}

	}

	public void checkIfUserExist(UserVO userVO) throws Exception {

		if (userVO != null && userVO.getUsername() != null) {
			UserVO dbUser = userService.getUserByUserName(userVO.getUsername());
			if (dbUser != null) {
				throw new Exception(
						messageSource.readValueByKey("registration.emailExist"));
			}
		}

	}

	public void changePassword(ChangePasswordVO changepassword)
			throws Exception {

		UserVO user = userService.getUserByUserName(UserUtility
				.getLoggedInUserUserName());
		user.setPassword(OnewayHash.generateStorngPasswordHash(changepassword
				.getNewPassword()));
		if (!user.isEmailVerified()) {
			changeEmailVerifiedInSessionForFirstTimeLogin();
		}
		userService.changepassword(user.getPassword());

	}

	private void changeEmailVerifiedInSessionForFirstTimeLogin() {
		CustomUserDetails uds = UserUtility.getLoggedInUser();
		if (uds != null) {
			Collection<? extends GrantedAuthority> authorities = uds
					.getAuthorities();
			uds.setEmailVerified(true);

			Authentication authentication = new UsernamePasswordAuthenticationToken(
					uds, "", authorities);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		}

	}

	public LoginCheckResponseVO checkUserLogin() {
		LoginCheckResponseVO response = new LoginCheckResponseVO();

		try {

			if (UserUtility.checkIfUserLoggedIn()) {

				UserVO vo = userService.getUserByUserName(UserUtility
						.getLoggedInUserUserName());
				if (vo.getRoles().contains(
						FreeMedicalOpinionRoles.Role.ROLE_USER)
						|| vo.getRoles().contains(
								FreeMedicalOpinionRoles.Role.ROLE_ADMIN)) {
					UserProfileVO proile = userProfileService
							.getUserProfileByUsername(vo.getUsername());
					response.setFname(proile.getFname());
					response.setLname(proile.getLname());
					response.setUsername(vo.getUsername());
					response.setRole(vo.getRoles().get(0));
					response.setLogin(true);
				} else if (vo.getRoles().contains(Role.ROLE_DOCTOR)) {
					DoctorProfileVO doc = doctorService.getDoctorByUsername(vo
							.getUsername());
					response.setFname(doc.getFname());
					response.setLname(doc.getLname());
					response.setUsername(doc.getUsername());
					response.setRole(vo.getRoles().get(0));
					response.setLogin(true);

				} else if (vo.getRoles().contains(Role.ROLE_HOSPITAL)) {
					HospitalProfileVO hospital = hospitalService
							.getHospitalByusername(vo.getUsername());
					response.setFname(hospital.getHospitalName());
					response.setUsername(vo.getUsername());
					response.setRole(vo.getRoles().get(0));
					response.setLogin(true);
				}

			} else {
				response.setFacebookLoginUrl("http://www.facebook.com/dialog/oauth?"
						+ "client_id="
						+ AppConstants.FB_APP_ID
						+ "&redirect_uri="
						+ URLEncoder.encode(AppConstants.REDIRECT_URI, "UTF-8")
						+ "&scope=email&display=popup");
				response.setLogin(false);

			}
			response.setResponseStatus(HTTP_RESPONSE.SUCCESS);
		} catch (Exception e) {
			response.setErrorMessage(messageSource
					.readValueByKey("freemedicalopinion.generalerror"));
			response.setResponseStatus(HTTP_RESPONSE.FAIL);

		}
		return response;
	}

	public boolean changeRole(Role role) throws Exception {

		userService.changeRole(role);
		return true;
	}

}
