package com.healthcare.freemedicalopinion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.healthcare.freemedicalopinion.manager.DoctorProfileManager;
import com.healthcare.freemedicalopinion.manager.HospitalProfileManager;
import com.healthcare.freemedicalopinion.manager.UserManager;
import com.healthcare.freemedicalopinion.manager.UserProfileManager;
import com.healthcare.freemedicalopinion.security.CustomUserDetailService;
import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Role;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

@Controller
public class AccountUpgradeController {

	@Autowired
	UserManager userManager;
	@Autowired
	UserProfileManager userProfileManager;

	@Autowired
	HospitalProfileManager hospitalManager;

	@Autowired
	DoctorProfileManager doctorManager;
	@Autowired
	CustomUserDetailService userService;

	/**
	 * User
	 * */
	@RequestMapping(value = { "/secure/upgradetodoctor" }, method = RequestMethod.POST)
	public @ResponseBody BaseResponseVO upgradetodoctor() {
		BaseResponseVO response = new BaseResponseVO(HTTP_RESPONSE.SUCCESS, "");
		try {
			userManager.changeRole(Role.ROLE_DOCTOR);
			UserProfileVO vo = userProfileManager
					.removeUserProfileOfLoggedInUser();
			doctorManager.addDoctor(vo);
			userService.refreshUserInSession();

		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
		}

		return response;
	}

	/**
	 * User
	 * */
	@RequestMapping(value = { "/secure/upgradetohospital" }, method = RequestMethod.POST)
	public @ResponseBody BaseResponseVO upgradetohospital(
			@RequestBody String hospitalName) {

		BaseResponseVO response = new BaseResponseVO(HTTP_RESPONSE.SUCCESS, "");
		try {
			userManager.changeRole(Role.ROLE_HOSPITAL);
			UserProfileVO vo = userProfileManager
					.removeUserProfileOfLoggedInUser();
			vo.setFname(hospitalName);
			hospitalManager.upgradeToHospital(vo);
			userService.refreshUserInSession();

		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
		}

		return response;
	}

	/**
	 * Only User
	 * */
	@RequestMapping(value = "/secure/getupgradetohospitalpopup", method = RequestMethod.GET)
	public ModelAndView getupgradetohospitalpopup() {
		return new ModelAndView("secure/upgradetohospitalpopup");
	}

}
