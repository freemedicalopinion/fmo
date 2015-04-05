package com.healthcare.freemedicalopinion.entityobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.valueobject.ProcedureVO;

@Document(collection = "procedure")
public class ProcedureEO {

	@Id
	private String id;
	private String procedureName;
	private String procedureId;
	private String description;
	private List<String> tags;

	public ProcedureEO() {

	}

	public ProcedureEO(ProcedureVO vo) {

		this.description = vo.getDescription();
		this.procedureId = vo.getProcedureId();
		this.procedureName = vo.getProcedureName();
		if (vo.getTags() != null) {
			if (this.tags == null) {
				this.tags = new ArrayList<String>();
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
	 * @return the procedureName
	 */
	public String getProcedureName() {
		return procedureName;
	}

	/**
	 * @param procedureName
	 *            the procedureName to set
	 */
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	/**
	 * @return the procedureId
	 */
	public String getProcedureId() {
		return procedureId;
	}

	/**
	 * @param procedureId
	 *            the procedureId to set
	 */
	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
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
