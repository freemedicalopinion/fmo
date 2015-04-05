package com.healthcare.freemedicalopinion.entityobject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.healthcare.freemedicalopinion.valueobject.SubCategoryVO;

public class SubCategoryEO {

	public SubCategoryEO() {

	}

	public SubCategoryEO(SubCategoryVO vo) {
		this.description = vo.getDescription();
		this.subCategoryId = vo.getSubCategoryId();
		this.subCategoryName = vo.getSubCategoryName();
		if (vo.getTags() != null) {
			this.tags = new HashSet<String>(Arrays.asList(vo.getTags().split(
					",")));
			this.tags.remove("");
		}
	}

	private String subCategoryName;
	private String subCategoryId;
	private String description;
	private Set<String> tags;

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

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

}
