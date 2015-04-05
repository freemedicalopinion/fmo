package com.healthcare.freemedicalopinion.valueobject;

import java.text.SimpleDateFormat;

import com.healthcare.freemedicalopinion.entityobject.HospitalReviewEO;
import com.healthcare.freemedicalopinion.utility.AppConstants;

public class HospitalReviewVO {

	private String reviewId;
	private String hospitalId;
	private String comment;
	private int staff;
	private int cleanliness;
	private int service;
	private int cost;
	private boolean isRecommended;
	private String createdDate;

	public HospitalReviewVO() {

	}

	public HospitalReviewVO(HospitalReviewEO eo) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				AppConstants.DATE_TIME_FORMAT);
		this.cleanliness = eo.getCleanliness();
		this.comment = eo.getComment();
		this.cost = eo.getCost();
		if(eo.getCreatedDate()!=null){
			this.createdDate = sdf.format(eo.getCreatedDate());
		}
		
		this.hospitalId = eo.getHospitalId();
		this.isRecommended = eo.isRecommended();
		this.reviewId = eo.getReviewId();
		this.service = eo.getService();
		this.staff = eo.getStaff();

	}

	/**
	 * @return the reviewId
	 */
	public String getReviewId() {
		return reviewId;
	}

	/**
	 * @param reviewId
	 *            the reviewId to set
	 */
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the staff
	 */
	public int getStaff() {
		return staff;
	}

	/**
	 * @param staff
	 *            the staff to set
	 */
	public void setStaff(int staff) {
		this.staff = staff;
	}

	/**
	 * @return the cleanliness
	 */
	public int getCleanliness() {
		return cleanliness;
	}

	/**
	 * @param cleanliness
	 *            the cleanliness to set
	 */
	public void setCleanliness(int cleanliness) {
		this.cleanliness = cleanliness;
	}

	/**
	 * @return the service
	 */
	public int getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(int service) {
		this.service = service;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the isRecommended
	 */
	public boolean isRecommended() {
		return isRecommended;
	}

	/**
	 * @param isRecommended
	 *            the isRecommended to set
	 */
	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
