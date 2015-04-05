package com.healthcare.freemedicalopinion.entityobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.valueobject.CategoryVO;
import com.healthcare.freemedicalopinion.valueobject.SubCategoryVO;

@Document(collection = "category")
public class CategoryEO {

	public CategoryEO() {

	}

	public CategoryEO(CategoryVO vo) {

		this.categoryId = vo.getCategoryId();
		this.description = vo.getDescription();
		this.name = vo.getName();
		this.imageName = vo.getImageName();
		if (vo.getTags() != null) {
			this.tags = new HashSet<String>(Arrays.asList(vo.getTags().split(
					",")));
			this.tags.remove("");

		}
		if (vo.getSubCategories() != null) {
			if (this.subCategories == null) {
				this.subCategories = new ArrayList<SubCategoryEO>();
			}
			for (SubCategoryVO subCategoryVO : vo.getSubCategories()) {
				this.subCategories.add(new SubCategoryEO(subCategoryVO));
			}
		}

	}

	@Id
	private String id;
	@Indexed(unique = true)
	private String name;
	@Indexed(unique = true)
	private String categoryId;
	private String imageName;
	private List<SubCategoryEO> subCategories;
	private String description;
	private Set<String> tags;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<SubCategoryEO> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategoryEO> subCategories) {
		this.subCategories = subCategories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
