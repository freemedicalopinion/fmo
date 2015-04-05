package com.healthcare.freemedicalopinion.valueobject;

import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.UserProfileEO;
import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Gender;

public class UserProfileVO {

	private String username;
	private String fname;
	private String lname;
	private String profilePicture;
	private List<String> contact;
	private String fbLink;
	private Gender gender;

	public UserProfileVO() {

	}

	public UserProfileVO(UserProfileEO eo) {
		this.contact = eo.getContact();
		this.fbLink = eo.getFbLink();
		this.lname = eo.getLname();
		this.fname = eo.getFname();
		this.gender = eo.getGender();
		this.profilePicture = eo.getProfilePicture();
		this.username = eo.getUsername();
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
	 * @return the profilePicture
	 */
	public String getProfilePicture() {
		return profilePicture;
	}

	/**
	 * @param profilePicture
	 *            the profilePicture to set
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * @return the contact
	 */
	public List<String> getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(List<String> contact) {
		this.contact = contact;
	}

	/**
	 * @return the fbLink
	 */
	public String getFbLink() {
		return fbLink;
	}

	/**
	 * @param fbLink
	 *            the fbLink to set
	 */
	public void setFbLink(String fbLink) {
		this.fbLink = fbLink;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
