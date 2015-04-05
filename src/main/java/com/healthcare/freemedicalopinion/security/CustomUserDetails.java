package com.healthcare.freemedicalopinion.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.healthcare.freemedicalopinion.entityobject.UserEO;
import com.healthcare.freemedicalopinion.valueobject.DoctorProfileVO;
import com.healthcare.freemedicalopinion.valueobject.HospitalProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

public class CustomUserDetails implements UserDetails {

	public CustomUserDetails() {

	}

	public CustomUserDetails(UserEO eo, UserProfileVO profileEO) {
		this.username = eo.getUsername();
		this.password = eo.getPassword();
		this.accountNonExpired = eo.isAccountNonExpired();
		this.accountNonLocked = eo.isAccountNonLocked();
		this.registerationApproved = eo.isRegisterationApproved();
		this.credentialsNonExpired = eo.isCredentialsNonExpired();
		this.isEmailVerified = eo.isEmailVerified();
		this.roles = Arrays.asList(eo.getRoles().get(0).toString());
		this.fname = profileEO.getFname();
		this.lname = profileEO.getLname();
	}

	public CustomUserDetails(UserEO eo, DoctorProfileVO doctorEO) {
		this.username = eo.getUsername();
		this.password = eo.getPassword();
		this.accountNonExpired = eo.isAccountNonExpired();
		this.accountNonLocked = eo.isAccountNonLocked();
		this.registerationApproved = eo.isRegisterationApproved();
		this.credentialsNonExpired = eo.isCredentialsNonExpired();
		this.isEmailVerified = eo.isEmailVerified();
		this.roles = Arrays.asList(eo.getRoles().get(0).toString());
		this.fname = doctorEO.getFname();
		this.lname = doctorEO.getLname();
	}

	public CustomUserDetails(UserEO eo, HospitalProfileVO hospitalEO) {
		this.username = eo.getUsername();
		this.password = eo.getPassword();
		this.accountNonExpired = eo.isAccountNonExpired();
		this.accountNonLocked = eo.isAccountNonLocked();
		this.registerationApproved = eo.isRegisterationApproved();
		this.credentialsNonExpired = eo.isCredentialsNonExpired();
		this.isEmailVerified = eo.isEmailVerified();
		this.roles = Arrays.asList(eo.getRoles().get(0).toString());
		this.fname = hospitalEO.getHospitalName();
	}

	private static final long serialVersionUID = -225948937372320405L;

	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean registerationApproved;
	private boolean credentialsNonExpired;
	private boolean isEmailVerified;
	private List<String> roles;
	private String fname;
	private String lname;

	public String getRolesCSV() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> iter = this.roles.iterator(); iter.hasNext();) {
			sb.append(iter.next());
			if (iter.hasNext()) {
				sb.append(',');
			}
		}
		return sb.toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(this
				.getRolesCSV());
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isEnabled() {
		return isAccountNonExpired() && isAccountNonLocked()
				&& isAccountNonLocked() && isCredentialsNonExpired()
				&& isEmailVerified();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	/**
	 * @return the registerationApproved
	 */
	public boolean isRegisterationApproved() {
		return registerationApproved;
	}

	/**
	 * @param registerationApproved
	 *            the registerationApproved to set
	 */
	public void setRegisterationApproved(boolean registerationApproved) {
		this.registerationApproved = registerationApproved;
	}

	/**
	 * @return the isEmailVerified
	 */
	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	/**
	 * @param isEmailVerified
	 *            the isEmailVerified to set
	 */
	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param accountNonExpired
	 *            the accountNonExpired to set
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @param accountNonLocked
	 *            the accountNonLocked to set
	 */
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

}
