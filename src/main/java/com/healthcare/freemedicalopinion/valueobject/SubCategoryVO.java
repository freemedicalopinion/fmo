package com.healthcare.freemedicalopinion.valueobject;

import com.healthcare.freemedicalopinion.entityobject.SubCategoryEO;

public class SubCategoryVO {

	public SubCategoryVO() {

	}

	public SubCategoryVO(SubCategoryEO eo) {
		this.description = eo.getDescription();
		this.subCategoryId = eo.getSubCategoryId();
		this.subCategoryName = eo.getSubCategoryName();
		if (eo.getTags() != null) {
			
			for (String tag : eo.getTags()) {
				if (this.tags == null) {
					this.tags = tag;
				}else{
					this.tags = this.tags + "," + tag;	
				}
				
			}

		}
	}

	private String subCategoryName;
	private String subCategoryId;
	private String description;
	private String tags;

	/**
	 * @return the subCategoryName
	 */
	public String getSubCategoryName() {
		return subCategoryName;
	}

	/**
	 * @param subCategoryName
	 *            the subCategoryName to set
	 */
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	/**
	 * @return the subCategoryId
	 */
	public String getSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * @param subCategoryId
	 *            the subCategoryId to set
	 */
	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
