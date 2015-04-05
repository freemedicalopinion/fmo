package com.healthcare.freemedicalopinion.valueobject;

import com.healthcare.freemedicalopinion.entityobject.SpecilityEO;

public class SpecilityVO {

	private String specilityName;
	private String specilityId;
	private String description;
	private String tags;

	public SpecilityVO() {

	}

	public SpecilityVO(SpecilityEO eo) {

		this.description = eo.getDescription();
		this.specilityId = eo.getSpecilityId();
		this.specilityName = eo.getSpecilityName();
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

	/**
	 * @return the specilityName
	 */
	public String getSpecilityName() {
		return specilityName;
	}

	/**
	 * @param specilityName
	 *            the specilityName to set
	 */
	public void setSpecilityName(String specilityName) {
		this.specilityName = specilityName;
	}

	/**
	 * @return the specilityId
	 */
	public String getSpecilityId() {
		return specilityId;
	}

	/**
	 * @param specilityId
	 *            the specilityId to set
	 */
	public void setSpecilityId(String specilityId) {
		this.specilityId = specilityId;
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

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	

}
