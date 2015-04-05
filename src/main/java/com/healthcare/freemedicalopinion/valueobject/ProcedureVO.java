package com.healthcare.freemedicalopinion.valueobject;

import com.healthcare.freemedicalopinion.entityobject.ProcedureEO;

public class ProcedureVO {

	private String procedureName;
	private String procedureId;
	private String description;
	private String tags;

	public ProcedureVO() {

	}

	public ProcedureVO(ProcedureEO eo) {
		this.description = eo.getDescription();
		this.procedureId = eo.getProcedureId();
		this.procedureName = eo.getProcedureName();
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
