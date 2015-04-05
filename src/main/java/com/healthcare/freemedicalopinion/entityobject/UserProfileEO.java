package com.healthcare.freemedicalopinion.entityobject;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles.Gender;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

@Document(collection = "userprofile")
public class UserProfileEO {

	@Id
	private String id;
	private String username;
	private String fname;
	private String lname;
	private String profilePicture;
	private List<String> contact;
	private String fbLink;
	private Gender gender;

	public UserProfileEO() {

	}

	public UserProfileEO(UserProfileVO vo) {
		this.contact = vo.getContact();
		this.fbLink = vo.getFbLink();
		this.fname = vo.getLname();
		this.lname = vo.getFname();
		this.gender = vo.getGender();
		this.profilePicture = vo.getProfilePicture();
		this.username = vo.getUsername();
	}

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
	 * 
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
