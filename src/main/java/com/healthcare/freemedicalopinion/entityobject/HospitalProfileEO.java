package com.healthcare.freemedicalopinion.entityobject;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.valueobject.HospitalProfileVO;

@Document(collection = "hospital")
public class HospitalProfileEO {
	@Id
	private String id;

	private String hospitalId;
	private String username;
	private String hospitalName;
	private String aboutHospital;
	private List<String> procedures;
	private String logo;
	private HospitalAddressEO address;
	private Set<String> contactNo;
	private Set<String> faxNo;
	private String fbLink;
	private String twitterLink;
	private String website;
	private ContentStatus status;
	private boolean ambulanceService;
	private Date updatedDate;

	public HospitalProfileEO() {

	}

	public HospitalProfileEO(HospitalProfileVO vo) {
		this.hospitalId = vo.getHospitalId();
		this.aboutHospital = vo.getAboutHospital();
		this.ambulanceService = vo.isAmbulanceService();
		this.hospitalName = vo.getHospitalName();
		this.username = vo.getUsername();
		this.procedures = vo.getProcedures();
		this.logo = vo.getLogo();
		this.status = vo.getStatus();
		if (vo.getAddress() != null) {
			this.address = new HospitalAddressEO(vo.getAddress());
		} else {
			this.address = null;
		}

		if (vo.getContactNo() != null) {

			this.contactNo = new HashSet<String>(Arrays.asList(vo
					.getContactNo().split(",")));
			this.contactNo.remove("");

		}
		if (vo.getFaxNo() != null) {

			this.faxNo = new HashSet<String>(Arrays.asList(vo.getFaxNo().split(
					",")));
			this.faxNo.remove("");

		}

		this.fbLink = vo.getFbLink();
		this.twitterLink = vo.getTwitterLink();
		this.website = vo.getWebsite();
		this.updatedDate=vo.getUpdatedDate();
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
	public HospitalAddressEO getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(HospitalAddressEO address) {
		this.address = address;
	}

	/**
	 * @return the contactNo
	 */
	public Set<String> getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo
	 *            the contactNo to set
	 */
	public void setContactNo(Set<String> contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the faxNo
	 */
	public Set<String> getFaxNo() {
		return faxNo;
	}

	/**
	 * @param faxNo
	 *            the faxNo to set
	 */
	public void setFaxNo(Set<String> faxNo) {
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
	 * @param ambulanceService
	 *            the ambulanceService to set
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
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
