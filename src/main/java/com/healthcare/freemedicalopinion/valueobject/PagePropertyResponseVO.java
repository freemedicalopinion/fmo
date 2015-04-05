package com.healthcare.freemedicalopinion.valueobject;

import java.util.Map;

public class PagePropertyResponseVO extends BaseResponseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1897715035503495935L;
	private String pageTitle;
	private Map<String, String> metaTags;
	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}
	/**
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	/**
	 * @return the metaTags
	 */
	public Map<String, String> getMetaTags() {
		return metaTags;
	}
	/**
	 * @param metaTags the metaTags to set
	 */
	public void setMetaTags(Map<String, String> metaTags) {
		this.metaTags = metaTags;
	}
	
	
	

}
