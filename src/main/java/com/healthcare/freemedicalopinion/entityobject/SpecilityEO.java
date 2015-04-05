package com.healthcare.freemedicalopinion.entityobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.valueobject.SpecilityVO;

@Document(collection = "specility")
public class SpecilityEO {

	@Id
	private String id;
	private String specilityName;
	private String specilityId;
	private String description;
	private List<String> tags;

	public SpecilityEO() {

	}

	public SpecilityEO(SpecilityVO vo) {

		this.description = vo.getDescription();
		this.specilityId = vo.getSpecilityId();
		this.specilityName = vo.getSpecilityName();
		if (vo.getTags() != null) {
			if(this.tags == null){
				this.tags=new ArrayList<String>();
			}
			this.tags.addAll(Arrays.asList(vo.getTags().split(",")));
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
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
