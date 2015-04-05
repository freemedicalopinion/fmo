package com.healthcare.freemedicalopinion.entityobject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Role;
import com.healthcare.freemedicalopinion.valueobject.UserVO;

@Document(collection = "user")
public class UserEO implements Serializable {

	private static final long serialVersionUID = -768355588757909686L;

	public UserEO() {

	}

	public UserEO(UserVO vo) {
		this.username = vo.getUsername();
		this.accountNonExpired = vo.isAccountNonExpired();
		this.accountNonLocked = vo.isAccountNonLocked();
		this.createdDate = vo.getCreatedDate();
		this.isEmailVerified = vo.isEmailVerified();
		this.registerationApproved = vo.isRegisterationApproved();
		this.roles = vo.getRoles();
		this.password = vo.getPassword();

	}

	@Id
	private String id;
	@Indexed(unique = true)
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean registerationApproved;
	private boolean credentialsNonExpired;
	private boolean isEmailVerified;
	private List<Role> roles;
	private Date createdDate;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

}