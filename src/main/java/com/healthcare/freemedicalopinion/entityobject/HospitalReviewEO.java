package com.healthcare.freemedicalopinion.entityobject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.utility.AppConstants;
import com.healthcare.freemedicalopinion.valueobject.HospitalReviewVO;

@Document(collection = "hospitalreview")
public class HospitalReviewEO {
	@Id
	private String id;
	private String reviewId;
	private String hospitalId;
	private String username;
	private String comment;
	private int staff;
	private int cleanliness;
	private int service;
	private int cost;
	private boolean isRecommended;
	private Date createdDate;

	public HospitalReviewEO() {

	}

	public HospitalReviewEO(HospitalReviewVO vo) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				AppConstants.DATE_TIME_FORMAT);

		this.cleanliness = vo.getCleanliness();
		this.comment = vo.getComment();
		this.cost = vo.getCost();
		if (vo.getCreatedDate() != null) {
			try {
				this.createdDate = sdf.parse(vo.getCreatedDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.hospitalId = vo.getHospitalId();
		this.isRecommended = vo.isRecommended();
		this.reviewId = vo.getReviewId();
		this.service = vo.getService();
		this.staff = vo.getStaff();

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
