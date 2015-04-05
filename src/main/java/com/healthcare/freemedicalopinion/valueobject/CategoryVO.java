package com.healthcare.freemedicalopinion.valueobject;

import java.util.ArrayList;
import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.CategoryEO;
import com.healthcare.freemedicalopinion.entityobject.SubCategoryEO;

public class CategoryVO {

	private String name;
	private String categoryId;
	private List<SubCategoryVO> subCategories;
	private String description;
	private String tags;
	private String imageName;

	public CategoryVO() {

	}

	public CategoryVO(CategoryEO eo) {

		this.categoryId = eo.getCategoryId();
		this.description = eo.getDescription();
		this.imageName=eo.getImageName();
		this.name = eo.getName();
		if (eo.getTags() != null) {

			for (String tag : eo.getTags()) {
				if (this.tags == null) {
					this.tags = tag;
				} else {
					this.tags = this.tags + "," + tag;
				}

			}

		}
		if (eo.getSubCategories() != null) {
			if (this.subCategories == null) {
				this.subCategories = new ArrayList<SubCategoryVO>();
			}
			for (SubCategoryEO subCategoryEO : eo.getSubCategories()) {
				this.subCategories.add(new SubCategoryVO(subCategoryEO));
			}
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public List<SubCategoryVO> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategoryVO> subCategories) {
		this.subCategories = subCategories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	

}
