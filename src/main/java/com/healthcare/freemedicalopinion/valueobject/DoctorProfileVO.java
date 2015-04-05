package com.healthcare.freemedicalopinion.valueobject;

import java.text.SimpleDateFormat;
import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.DoctorProfileEO;
import com.healthcare.freemedicalopinion.utility.AppConstants;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;

public class DoctorProfileVO {

	private String fname;
	private String lname;
	private String username;
	private String fbLink;
	private String twitterLink;
	private List<String> specilities;
	private List<String> hospitals;
	private String doctorId;
	private String updatedDate;
	private ContentStatus status;

	public DoctorProfileVO() {

	}

	public DoctorProfileVO(DoctorProfileEO eo) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				AppConstants.DATE_TIME_FORMAT);
		this.fname = eo.getFname();
		this.lname = eo.getLname();
		this.username = eo.getUsername();
		this.doctorId = eo.getDoctorId();
		this.fbLink = eo.getFbLink();
		this.hospitals = eo.getHospitals();
		this.specilities = eo.getSpecilities();
		this.twitterLink = eo.getTwitterLink();
		this.status=eo.getStatus();
		if (eo.getUpdatedDate() != null) {
			this.updatedDate = sdf.format(eo.getUpdatedDate());
		}

	}

	public DoctorProfileVO(UserProfileVO vo) {
		this.fname = vo.getFname();
		this.lname = vo.getLname();
		this.username = vo.getUsername();

	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 * 
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
	 * @return the twitterLink
	 */
	public String getTwitterLink() {
		return twitterLink;
	}

	/**
	 * @param twitterLink
	 *            the twitterLink to set
	 */
	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}

	/**
	 * @return the specilities
	 */
	public List<String> getSpecilities() {
		return specilities;
	}

	/**
	 * @param specilities
	 *            the specilities to set
	 */
	public void setSpecilities(List<String> specilities) {
		this.specilities = specilities;
	}

	/**
	 * @return the hospitals
	 */
	public List<String> getHospitals() {
		return hospitals;
	}

	/**
	 * @param hospitals
	 *            the hospitals to set
	 */
	public void setHospitals(List<String> hospitals) {
		this.hospitals = hospitals;
	}

	/**
	 * @return the doctorId
	 */
	public String getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId
	 *            the doctorId to set
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the updatedDate
	 */
	public String getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the status
	 */
	public ContentStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ContentStatus status) {
		this.status = status;
	}

	
}
