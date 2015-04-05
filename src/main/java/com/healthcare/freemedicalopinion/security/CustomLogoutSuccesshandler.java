package com.healthcare.freemedicalopinion.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class CustomLogoutSuccesshandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		/*
		 * if (((CustomUserDetails)
		 * authentication.getPrincipal()).isLoginViaInternalUser()) {
		 * SecurityContextHolder.getContext().setAuthentication(
		 * (Authentication) ((UserVO) authentication.getPrincipal())
		 * .getOldOAuth()); response.sendRedirect("secure/internalUserLogin"); }
		 * else { super.onLogoutSuccess(request, response, authentication); }
		 */
		super.onLogoutSuccess(request, response, authentication);
	}
}
