package com.healthcare.freemedicalopinion.valueobject;

import java.util.Date;
import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.HospitalProfileEO;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;

public class HospitalProfileVO {
	private String hospitalId;
	private String username;
	private String hospitalName;
	private String aboutHospital;
	private List<String> procedures;
	private String logo;
	private HospitalAddressVO address;
	private String contactNo;
	private String faxNo;
	private String fbLink;
	private String twitterLink;
	private String website;
	private ContentStatus status;
	private boolean ambulanceService;
	private Date updatedDate;

	public HospitalProfileVO() {

	}

	public HospitalProfileVO(HospitalProfileEO eo) {
		this.hospitalId = eo.getHospitalId();
		this.status = eo.getStatus();
		this.aboutHospital = eo.getAboutHospital();
		this.hospitalName = eo.getHospitalName();
		this.username = eo.getUsername();
		this.procedures = eo.getProcedures();
		this.logo = eo.getLogo();
		this.ambulanceService=eo.isAmbulanceService();
		if (eo.getAddress() != null) {
			this.address = new HospitalAddressVO(eo.getAddress());
		} else {
			this.address = null;
		}

		if (eo.getContactNo() != null) {

			for (String contact : eo.getContactNo()) {
				if (this.contactNo == null) {
					this.contactNo = contact;
				} else {
					this.contactNo = this.contactNo + "," + contact;
				}

			}

		}
		if (eo.getFaxNo() != null) {

			for (String fax : eo.getFaxNo()) {
				if (this.faxNo == null) {
					this.faxNo = fax;
				} else {
					this.faxNo = this.faxNo + "," + fax;
				}

			}

		}

		this.fbLink = eo.getFbLink();
		this.twitterLink = eo.getTwitterLink();
		this.website = eo.getWebsite();
		this.updatedDate=eo.getUpdatedDate();
	}

	public HospitalProfileVO(UserProfileVO vo) {
		this.hospitalName = vo.getFname();
		this.username = vo.getUsername();
	}

	/**
	 * @return the hospitalId
	 */
	public String getHospitalId() {
		return hospitalId;
	}

	/**
	 * @param hospitalId
	 *            the hospitalId to set
	 */
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
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
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @param hospitalName
	 *            the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * @return the aboutHospital
	 */
	public String getAboutHospital() {
		return aboutHospital;
	}

	/**
	 * @param aboutHospital
	 *            the aboutHospital to set
	 */
	public void setAboutHospital(String aboutHospital) {
		this.aboutHospital = aboutHospital;
	}

	/**
	 * @return the procedures
	 */
	public List<String> getProcedures() {
		return procedures;
	}

	/**
	 * @param procedures
	 *            the procedures to set
	 */
	public void setProcedures(List<String> procedures) {
		this.procedures = procedures;
	}

	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @return the address
	 */
	public HospitalAddressVO getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(HospitalAddressVO address) {
		this.address = address;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo
	 *            the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * @param faxNo
	 *            the faxNo to set
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
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
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the status
	 */
	public ContentStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ContentStatus status) {
		this.status = status;
	}

	/**
	 * @return the ambulanceService
	 */
	public boolean isAmbulanceService() {
		return ambulanceService;
	}

	/**
	 * @param ambulanceService the ambulanceService to set
	 */
	public void setAmbulanceService(boolean ambulanceService) {
		this.ambulanceService = ambulanceService;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
