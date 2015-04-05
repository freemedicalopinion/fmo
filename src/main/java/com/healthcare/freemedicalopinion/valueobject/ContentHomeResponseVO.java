package com.healthcare.freemedicalopinion.valueobject;

import java.util.List;

public class ContentHomeResponseVO extends PagePropertyResponseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6665844233171243171L;
	
	private List<ContentVO> contents;

	/**
	 * @return the contents
	 */
	public List<ContentVO> getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(List<ContentVO> contents) {
		this.contents = contents;
	}
	
	
	
	
}
