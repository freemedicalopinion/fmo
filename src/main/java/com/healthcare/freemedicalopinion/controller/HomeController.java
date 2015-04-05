package com.healthcare.freemedicalopinion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.freemedicalopinion.manager.UserManager;
import com.healthcare.freemedicalopinion.utility.OnewayHash;
import com.healthcare.freemedicalopinion.utility.ReadValueFromMessageSource;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.ChangePasswordVO;

@Controller
public class HomeController {

	@Autowired
	UserManager userManager;
	@Autowired
	ReadValueFromMessageSource message;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView doGet() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView doGet1() {
		return new ModelAndView("public/index");
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure", method = RequestMethod.GET)
	public ModelAndView secureHome() {
		ModelAndView mav = new ModelAndView();

		if (UserUtility.getLoggedInUser() != null
				&& !UserUtility.getLoggedInUser().isEmailVerified()) {
			mav.addObject("changePassword", new ChangePasswordVO());
			mav.setViewName("secure/changepassword");
			return mav;
		}
		mav.setViewName("secure/index");
		return mav;
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure/home", method = RequestMethod.GET)
	public ModelAndView secureIndex() {

		return new ModelAndView("secure/home");
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure/home", method = RequestMethod.POST)
	public @ResponseBody BaseResponseVO changepassword(
			@RequestBody ChangePasswordVO changepassword) {

		String error = null;
		BaseResponseVO response = new BaseResponseVO();
		try {
			if (OnewayHash.validatePassword(changepassword.getOldPassword(),
					UserUtility.getLoggedInUser().getPassword())) {
				if (changepassword.getNewPassword().equals(
						changepassword.getConfirmNewPassword())) {

					userManager.changePassword(changepassword);
					response.setMessage(message
							.readValueByKey("password.cahngesuccess"));
					response.setResponseStatus(HTTP_RESPONSE.SUCCESS);

				} else {
					error = message.readValueByKey("password.notmatch");
					response.setErrorMessage(error);
					response.setResponseStatus(HTTP_RESPONSE.FAIL);
				}
			} else {
				error = message.readValueByKey("password.notmatchFromDB");
				response.setErrorMessage(error);
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
			}
		} catch (Exception e) {
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.generalerror"));
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
		}

		return response;
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = "SearchHome.html", method = RequestMethod.GET)
	public ModelAndView search() {

		return new ModelAndView("public/SearchHome");
	}
}
