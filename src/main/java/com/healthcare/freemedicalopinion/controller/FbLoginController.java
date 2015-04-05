package com.healthcare.freemedicalopinion.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healthcare.freemedicalopinion.manager.UserManager;
import com.healthcare.freemedicalopinion.security.CustomUserDetailService;
import com.healthcare.freemedicalopinion.security.FacebookAuthenticationProvider;
import com.healthcare.freemedicalopinion.utility.GetFBUserData;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserVO;

@Controller
public class FbLoginController {

	@Autowired
	UserManager manager;
	@Autowired
	CustomUserDetailService customUserDetailService;
	@Autowired
	FacebookAuthenticationProvider fbLogin;

	private String code;

	@RequestMapping(value = "/fbLogin", method = RequestMethod.GET)
	public String fbLogin(HttpServletRequest req, HttpServletResponse res) {

		code = req.getParameter("code");

		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}

		GetFBUserData fb = new GetFBUserData(code);
		fb.getAccessToken();
		UserProfileVO user = null;
		try {
			user = fb.getFBGraph();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user != null) {
			UserVO userdb = manager.getUserbyUsername(user.getUsername());
			if (userdb == null) {
				try {
					manager.createNewUser(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			login(user.getUsername(), req);
		}

		return "public/closeWindow";
	}

	private void login(String userName, HttpServletRequest req) {

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				userName, "");

		// Authenticate the user
		Authentication authentication = fbLogin.authenticate(authRequest);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);

		// Create a new session and add the security context.
		HttpSession session = req.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

	}

}
