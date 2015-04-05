package com.healthcare.freemedicalopinion.valueobject;

import java.text.SimpleDateFormat;

import com.healthcare.freemedicalopinion.entityobject.CommentEO;
import com.healthcare.freemedicalopinion.utility.AppConstants;

public class CommentVO {

	private String comment;
	private String commentId;
	private String userId;
	private String contentId;
	private String fname;
	private String lname;
	private String updated;

	public CommentVO() {

	}

	public CommentVO(CommentEO eo) {
		SimpleDateFormat sdf=new SimpleDateFormat(AppConstants.DATE_TIME_FORMAT);
		this.comment = eo.getComment();
		this.commentId = eo.getCommentId();
		this.contentId = eo.getContentId();
		this.userId = eo.getUserId();
		this.fname = eo.getFname();
		this.lname = eo.getLname();
		this.updated = sdf.format(eo.getUpdated());
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
	 * @return the commentId
	 */
	public String getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId
	 *            the commentId to set
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentId
	 *            the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
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
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *            the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}

}
