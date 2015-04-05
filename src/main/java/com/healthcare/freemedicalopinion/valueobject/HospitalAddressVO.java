package com.healthcare.freemedicalopinion.valueobject;

import com.healthcare.freemedicalopinion.entityobject.HospitalAddressEO;

public class HospitalAddressVO {

	private String address;
	private String city;
	private String state;
	private String country;

	public HospitalAddressVO() {

	}

	public HospitalAddressVO(HospitalAddressEO eo) {
		this.address = eo.getAddress();
		this.city = eo.getCity();
		this.state = eo.getState();
		this.country = eo.getCountry();

	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}
