package com.healthcare.freemedicalopinion.valueobject;

import com.healthcare.freemedicalopinion.entityobject.AddressCityEO;

public class AddressCityVO {

	private String cityName;
	private String cityId;

	public AddressCityVO() {

	}

	public AddressCityVO(AddressCityEO eo) {
		this.cityId=eo.getCityId();
		this.cityName=eo.getCityName();
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

}
