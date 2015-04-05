package com.healthcare.freemedicalopinion.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class FacebookAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = null;
		if (authentication.getName() != null && authentication.getName() != "") {
			username = authentication.getName();
		} else {
			throw new BadCredentialsException("Wrong password.");
		}

		CustomUserDetails uds = customUserDetailService
				.loadUserByUsername(username);
		uds.setEmailVerified(true);

		if (uds != null) {

			Collection<? extends GrantedAuthority> authorities = uds
					.getAuthorities();

			return new UsernamePasswordAuthenticationToken(uds, "", authorities);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

	/**
	 * @return the userDetailsService
	 */
	public CustomUserDetailService getUserDetailsService() {
		return customUserDetailService;
	}

	/**
	 * @param userDetailsService
	 *            the userDetailsService to set
	 */
	public void setUserDetailsService(CustomUserDetailService userDetailsService) {
		this.customUserDetailService = userDetailsService;
	}

}
