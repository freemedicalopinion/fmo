package com.healthcare.freemedicalopinion.valueobject;

public class MenuEO {

	private String menulabel;
	private String url;

	public MenuEO() {

	}

	public MenuEO(String menulabel, String url) {
		this.menulabel = menulabel;
		this.url = url;
	}

	/**
	 * @return the menulabel
	 */
	public String getMenulabel() {
		return menulabel;
	}

	/**
	 * @param menulabel
	 *            the menulabel to set
	 */
	public void setMenulabel(String menulabel) {
		this.menulabel = menulabel;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
