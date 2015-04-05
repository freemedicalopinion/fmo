package com.healthcare.freemedicalopinion.entityobject;

import com.healthcare.freemedicalopinion.valueobject.AddressCityVO;

public class AddressCityEO {

	private String cityName;
	private String cityId;
	
	public AddressCityEO() {

	}

	public AddressCityEO(AddressCityVO vo) {
		this.cityId=vo.getCityId();
		this.cityName=vo.getCityName();
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
