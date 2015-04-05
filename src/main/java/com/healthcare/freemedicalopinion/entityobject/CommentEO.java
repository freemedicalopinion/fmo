package com.healthcare.freemedicalopinion.entityobject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.utility.AppConstants;
import com.healthcare.freemedicalopinion.valueobject.CommentVO;

@Document(collection = "comment")
public class CommentEO {
	@Id
	private String id;

	private String comment;
	private String commentId;
	private String userId;
	private String contentId;
	private String fname;
	private String lname;
	private Date updated;

	public CommentEO() {

	}

	public CommentEO(CommentVO vo) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat(AppConstants.DATE_TIME_FORMAT);
		this.comment = vo.getComment();
		this.commentId = vo.getCommentId();
		this.contentId = vo.getContentId();
		this.userId = vo.getUserId();
		this.fname = vo.getFname();
		this.lname = vo.getLname();
		if(vo.getUpdated()!=null){
			this.updated = sdf.parse(vo.getUpdated());	
		}
		
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
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *            the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
