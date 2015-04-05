package com.healthcare.freemedicalopinion.valueobject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.UserEO;
import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Role;

public class UserVO implements Serializable {

	private static final long serialVersionUID = -768355588757909686L;

	public UserVO() {

	}

	public UserVO(UserEO eo) {

		this.username = eo.getUsername();
		this.accountNonExpired = eo.isAccountNonExpired();
		this.accountNonLocked = eo.isAccountNonLocked();
		this.createdDate = eo.getCreatedDate();
		this.isEmailVerified = eo.isEmailVerified();
		this.registerationApproved = eo.isRegisterationApproved();
		this.roles=eo.getRoles();

	}

	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean registerationApproved;
	private boolean credentialsNonExpired;
	private boolean isHomePageCreated;
	private boolean isEmailVerified;
	private List<Role> roles;
	private Date createdDate;
	private boolean loginViaInternalUser;
	private Object oldOAuth;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the accountNonExpired
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @param accountNonExpired
	 *            the accountNonExpired to set
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @param accountNonLocked
	 *            the accountNonLocked to set
	 */
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
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
	 * @return the credentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @return the isHomePageCreated
	 */
	public boolean isHomePageCreated() {
		return isHomePageCreated;
	}

	/**
	 * @param isHomePageCreated
	 *            the isHomePageCreated to set
	 */
	public void setHomePageCreated(boolean isHomePageCreated) {
		this.isHomePageCreated = isHomePageCreated;
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
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the loginViaInternalUser
	 */
	public boolean isLoginViaInternalUser() {
		return loginViaInternalUser;
	}

	/**
	 * @param loginViaInternalUser
	 *            the loginViaInternalUser to set
	 */
	public void setLoginViaInternalUser(boolean loginViaInternalUser) {
		this.loginViaInternalUser = loginViaInternalUser;
	}

	/**
	 * @return the oldOAuth
	 */
	public Object getOldOAuth() {
		return oldOAuth;
	}

	/**
	 * @param oldOAuth
	 *            the oldOAuth to set
	 */
	public void setOldOAuth(Object oldOAuth) {
		this.oldOAuth = oldOAuth;
	}

}
