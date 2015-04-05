package com.healthcare.freemedicalopinion.entityobject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.utility.AppConstants;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.valueobject.DoctorProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

@Document(collection = "doctor")
public class DoctorProfileEO {

	@Id
	private String id;
	private String fname;
	private String lname;
	private String doctorId;
	private String username;
	private String fbLink;
	private String twitterLink;
	private List<String> specilities;
	private List<String> hospitals;
	private Date updatedDate;
	private ContentStatus status;
	
	public DoctorProfileEO() {

	}

	public DoctorProfileEO(DoctorProfileVO vo) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				AppConstants.DATE_TIME_FORMAT);
		this.fname = vo.getFname();
		this.lname = vo.getLname();
		this.username = vo.getUsername();
		this.doctorId = vo.getDoctorId();
		this.fbLink = vo.getFbLink();
		this.hospitals = vo.getHospitals();
		this.specilities = vo.getSpecilities();
		this.twitterLink = vo.getTwitterLink();
		this.status=vo.getStatus();
		if (vo.getUpdatedDate() != null) {
			try {
				this.updatedDate = sdf.parse(vo.getUpdatedDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

	public DoctorProfileEO(UserProfileVO vo) {
		this.fname = vo.getFname();
		this.lname = vo.getLname();
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
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
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
