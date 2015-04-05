package com.healthcare.freemedicalopinion.valueobject;

public class ContentValidateResponseVO extends BaseResponseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8205755779938037474L;

	private boolean categoryId;
	private boolean subCategoryId;
	private boolean title;
	private boolean imageName;
	private boolean contentBody;
	private boolean status;
	private boolean tags;

	private boolean isValid;

	/**
	 * @return the categoryId
	 */
	public boolean isCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(boolean categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the subCategoryId
	 */
	public boolean isSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * @param subCategoryId
	 *            the subCategoryId to set
	 */
	public void setSubCategoryId(boolean subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	/**
	 * @return the title
	 */
	public boolean isTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(boolean title) {
		this.title = title;
	}

	/**
	 * @return the imageName
	 */
	public boolean isImageName() {
		return imageName;
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(boolean imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the contentBody
	 */
	public boolean isContentBody() {
		return contentBody;
	}

	/**
	 * @param contentBody
	 *            the contentBody to set
	 */
	public void setContentBody(boolean contentBody) {
		this.contentBody = contentBody;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the tags
	 */
	public boolean isTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(boolean tags) {
		this.tags = tags;
	}

	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return (!this.categoryId && !this.subCategoryId && !this.title
				&& !this.contentBody && !this.status);
	}

	/**
	 * @param isValid
	 *            the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
