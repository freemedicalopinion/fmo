package com.healthcare.freemedicalopinion.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.UserEO;
import com.healthcare.freemedicalopinion.repository.UserRepository;
import com.healthcare.freemedicalopinion.security.CustomUserDetails;
import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Role;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.DoctorProfileVO;
import com.healthcare.freemedicalopinion.valueobject.HospitalProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	DoctorService doctorService;
	@Autowired
	HospitalService hospitalService;

	@Override
	public UserVO getUserByUserName(String username) {
		if (username != null) {
			List<UserEO> userEO = userRepository.findByUsername(username);
			if (userEO != null && userEO.size() > 0) {
				return new UserVO(userEO.get(0));
			}
		}
		return null;
	}

	@Override
	public void addUser(UserProfileVO profile, UserVO user) throws Exception {
		if (user != null) {
			if (getUserByUserName(user.getUsername()) != null) {
				throw new Exception();
			}
			userRepository.save(addDefaultValueToNewUser(new UserEO(user)));
			userProfileService.addUserProfile(profile);

		}

	}

	@Override
	public CustomUserDetails getUserDetailsForLogin(String username) {
		if (username != null) {
			List<UserEO> userEO = userRepository.findByUsername(username);
			if (userEO != null && userEO.size() > 0) {
				UserEO eo = userEO.get(0);
				if (eo.getRoles().contains(Role.ROLE_ADMIN)
						|| eo.getRoles().contains(Role.ROLE_USER)) {
					UserProfileVO profile = userProfileService
							.getUserProfileByUsername(username);

					return new CustomUserDetails(userEO.get(0), profile);
				} else if (eo.getRoles().contains(Role.ROLE_DOCTOR)) {
					DoctorProfileVO doc = doctorService
							.getDoctorByUsername(username);
					return new CustomUserDetails(userEO.get(0), doc);

				} else if (eo.getRoles().contains(Role.ROLE_HOSPITAL)) {
					HospitalProfileVO hospital = hospitalService
							.getHospitalByusername(username);
					return new CustomUserDetails(userEO.get(0), hospital);
				}

			}
		}
		return null;
	}

	@Override
	public void changepassword(String hashedpassword) throws Exception {
		if (hashedpassword != null) {
			List<UserEO> userEO = userRepository.findByUsername(UserUtility
					.getLoggedInUserUserName());
			if (userEO != null && userEO.size() > 0) {
				UserEO eo = userEO.get(0);
				eo.setPassword(hashedpassword);
				eo.setEmailVerified(true);
				userRepository.save(userEO.get(0));
			}
		}

	}

	private UserEO addDefaultValueToNewUser(UserEO eo) {
		if (eo != null) {
			eo.setCreatedDate(new Date());
			eo.setAccountNonExpired(true);
			eo.setAccountNonLocked(true);
			eo.setCredentialsNonExpired(true);
			eo.setEmailVerified(false);
			eo.setRegisterationApproved(true);
			eo.setRoles(Arrays.asList(Role.ROLE_USER));

		}

		return eo;

	}

	@Override
	public boolean changeRole(Role newRole) throws Exception {
		List<UserEO> usereos = userRepository.findByUsername(UserUtility
				.getLoggedInUserUserName());

		if (usereos != null && usereos.size() > 0) {

			UserEO eo = usereos.get(0);

			List<Role> roles = new ArrayList<Role>();
			roles.add(newRole);
			eo.setRoles(roles);
			userRepository.save(eo);

			return true;

		}
		return false;
	}

}
