package com.healthcare.freemedicalopinion.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.manager.UserManager;
import com.healthcare.freemedicalopinion.utility.UserUtility;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserManager userManager;

	@Override
	public CustomUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if (username == null || username == "") {
			return null;
		}
		CustomUserDetails user = userManager.getUserDetailForLogin(username);
		if (user == null) {
			return null;
		}
		return user;
	}

	public void refreshUserInSession() {
		UserDetails uds = loadUserByUsername(UserUtility
				.getLoggedInUserUserName());
		if (uds != null) {
			Collection<? extends GrantedAuthority> authorities = uds
					.getAuthorities();
			CustomUserDetails user = (CustomUserDetails) uds;
			user.setEmailVerified(true);
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					user, "", authorities);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		}

	}

}
