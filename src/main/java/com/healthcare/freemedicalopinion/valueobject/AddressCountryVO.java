package com.healthcare.freemedicalopinion.valueobject;

import java.util.ArrayList;
import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.AddressCountryEO;
import com.healthcare.freemedicalopinion.entityobject.AddressStateEO;

public class AddressCountryVO {

	private String countryId;

	private String countryName;

	private List<AddressStateVO> states;

	public AddressCountryVO() {

	}

	public AddressCountryVO(AddressCountryEO eo) {

		this.countryId = eo.getCountryId();
		this.countryName = eo.getCountryName();

		if (eo.getStates() != null) {

			this.states = new ArrayList<AddressStateVO>();

			for (AddressStateEO eoState : eo.getStates()) {
				this.states.add(new AddressStateVO(eoState));
			}
		}

	}

	/**
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the states
	 */
	public List<AddressStateVO> getStates() {
		return states;
	}

	/**
	 * @param states
	 *            the states to set
	 */
	public void setStates(List<AddressStateVO> states) {
		this.states = states;
	}

}
