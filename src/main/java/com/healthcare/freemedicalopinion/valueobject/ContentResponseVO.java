package com.healthcare.freemedicalopinion.valueobject;

import java.util.List;

import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;

public class ContentResponseVO {

	private List<ContentVO> allContent;
	private List<ContentStatus> allStatuses;

	/**
	 * @return the allContent
	 */
	public List<ContentVO> getAllContent() {
		return allContent;
	}

	/**
	 * @param allContent
	 *            the allContent to set
	 */
	public void setAllContent(List<ContentVO> allContent) {
		this.allContent = allContent;
	}

	/**
	 * @return the allStatuses
	 */
	public List<ContentStatus> getAllStatuses() {
		return allStatuses;
	}

	/**
	 * @param allStatuses
	 *            the allStatuses to set
	 */
	public void setAllStatuses(List<ContentStatus> allStatuses) {
		this.allStatuses = allStatuses;
	}

}
