package com.healthcare.freemedicalopinion.valueobject;

import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Role;

public class LoginCheckResponseVO extends BaseResponseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3071942443503932769L;

	private String facebookLoginUrl;
	private boolean isLogin;
	private String fname;
	private String lname;
	private String username;
	private Role role;

	/**
	 * @return the facebookLoginUrl
	 */
	public String getFacebookLoginUrl() {
		return facebookLoginUrl;
	}

	/**
	 * @param facebookLoginUrl
	 *            the facebookLoginUrl to set
	 */
	public void setFacebookLoginUrl(String facebookLoginUrl) {
		this.facebookLoginUrl = facebookLoginUrl;
	}

	/**
	 * @return the isLogin
	 */
	public boolean isLogin() {
		return isLogin;
	}

	/**
	 * @param isLogin
	 *            the isLogin to set
	 */
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}
