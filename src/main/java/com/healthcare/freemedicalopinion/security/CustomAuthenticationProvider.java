package com.healthcare.freemedicalopinion.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.utility.OnewayHash;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = null;
		String password = null;
		if (authentication.getName() != null && authentication.getName() != "") {
			username = authentication.getName();
		} else {
			throw new BadCredentialsException("Wrong password.");
		}

		if (authentication.getCredentials() != null
				&& authentication.getCredentials() != "") {
			password = authentication.getCredentials().toString();
		} else {
			throw new BadCredentialsException("Wrong password.");
		}

		UserDetails uds = customUserDetailService.loadUserByUsername(username);

		if (uds != null) {
			CustomUserDetails uso = (CustomUserDetails) uds;
			

			try {
				if (!OnewayHash.validatePassword(password, uso.getPassword())) {
					throw new BadCredentialsException("Wrong password.");
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if (!uso.isRegisterationApproved()) {
				throw new LockedException("Registration Not Approved");
			} else if (!uso.isCredentialsNonExpired()) {
				throw new CredentialsExpiredException("Credential Expired");
			} else if (!uso.isAccountNonLocked()) {
				throw new AccountExpiredException("Account is locked");
			}
		} else {
			throw new BadCredentialsException("User is Not Registered");
		}

		Collection<? extends GrantedAuthority> authorities = uds
				.getAuthorities();

		return new UsernamePasswordAuthenticationToken(uds, password,
				authorities);
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
