package com.healthcare.freemedicalopinion.valueobject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.ContentEO;
import com.healthcare.freemedicalopinion.utility.AppConstants;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;

public class ContentVO {

	public ContentVO() {

	}

	public ContentVO(ContentEO eo) {
		SimpleDateFormat fmt = new SimpleDateFormat(AppConstants.DATE_TIME_FORMAT);

		this.categoryId = eo.getCategoryId();
		this.contentBody = eo.getContentBody();
		this.contentId = eo.getContentId();
		if (eo.getContentId() != null) {
			this.createdDate = fmt.format(new Date(Long.valueOf(eo
					.getContentId())));
		}

		this.imageName = eo.getImageName();
		this.status = eo.getStatus();
		this.subCategoryId = eo.getSubCategoryId();
		this.title = eo.getTitle();
		if (eo.getUpdatedDate() != null) {
			this.updatedDate = fmt.format(eo.getUpdatedDate());
		}else{
			this.updatedDate=this.createdDate;
		}

		this.author = eo.getAuthor();
		this.comment = eo.getComment();
		if (eo.getTags() != null) {

			for (String tag : eo.getTags()) {
				if (this.tags == null) {
					this.tags = tag;
				} else {
					this.tags = this.tags + "," + tag;
				}

			}

		}

	}

	private String contentId;
	private String categoryId;
	private List<String> subCategoryId;
	private String title;
	private String imageName;
	private String contentBody;
	private ContentStatus status;
	private String createdDate;
	private String updatedDate;
	private String tags;
	private String author;
	private String comment;

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the subCategoryId
	 */
	public List<String> getSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * @param subCategoryId
	 *            the subCategoryId to set
	 */
	public void setSubCategoryId(List<String> subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getContentBody() {
		return contentBody;
	}

	public void setContentBody(String contentBody) {
		this.contentBody = contentBody;
	}

	public ContentStatus getStatus() {
		return status;
	}

	public void setStatus(ContentStatus status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

}
