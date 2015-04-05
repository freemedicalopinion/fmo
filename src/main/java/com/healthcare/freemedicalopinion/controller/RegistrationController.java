package com.healthcare.freemedicalopinion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.freemedicalopinion.manager.UserManager;
import com.healthcare.freemedicalopinion.utility.ReadValueFromMessageSource;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.LoginCheckResponseVO;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserVO;

@Controller
public class RegistrationController {

	@Autowired
	UserManager userManager;
	@Autowired
	ReadValueFromMessageSource message;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView doGet(
			@RequestParam(value = "error", required = false) String error) {

		ModelAndView m = new ModelAndView();
		m.addObject("loginError", error);
		m.setViewName("public/login");

		return m;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponseVO registerUser(
			@RequestBody UserProfileVO user) {
		try {
			userManager.createNewUser(user);
		} catch (Exception e) {
			return new BaseResponseVO(HTTP_RESPONSE.FAIL, e.getMessage());
		}
		return new BaseResponseVO(HTTP_RESPONSE.SUCCESS,
				message.readValueByKey("registration.succcess"));
	}

	@RequestMapping(value = "/checkUserExist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponseVO checkUserExist(@RequestBody UserVO user) {
		try {
			userManager.checkIfUserExist(user);
		} catch (Exception e) {
			return new BaseResponseVO(HTTP_RESPONSE.FAIL, e.getMessage());
		}
		return new BaseResponseVO(HTTP_RESPONSE.SUCCESS);
	}

	/**
	 * common
	 * */
	@RequestMapping(value = { "/checkUserLogin", "/secure/checkUserLogin" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody LoginCheckResponseVO checkUserExist() {

		return userManager.checkUserLogin();
	}

}
