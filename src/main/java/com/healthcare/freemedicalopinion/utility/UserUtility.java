package com.healthcare.freemedicalopinion.utility;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.healthcare.freemedicalopinion.security.CustomUserDetails;

public class UserUtility {
	public static List<String> getLoggedInUserRoleList() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		CustomUserDetails user = null;
		if (auth != null) {
			user = (CustomUserDetails) auth.getPrincipal();
		}
		return user.getRoles();

	}

	public static boolean checkIfUserLoggedIn() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			return true;
		}
		return false;

	}

	public static String getLoggedInUserUserName() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			return auth.getName();
		}
		return null;

	}

	public static CustomUserDetails getLoggedInUser() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			return user;
		}
		return null;
	}

	

}
