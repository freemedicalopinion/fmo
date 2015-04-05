package com.healthcare.freemedicalopinion.valueobject;

import java.util.ArrayList;
import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.AddressCityEO;
import com.healthcare.freemedicalopinion.entityobject.AddressStateEO;

public class AddressStateVO {

	private String stateId;
	private String stateName;
	private List<AddressCityVO> cities;

	public AddressStateVO() {

	}

	public AddressStateVO(AddressStateEO eo) {
		this.stateId = eo.getStateId();
		this.stateName = eo.getStateName();
		if (eo.getCities() != null) {

			this.cities = new ArrayList<AddressCityVO>();

			for (AddressCityEO eoCity : eo.getCities()) {
				this.cities.add(new AddressCityVO(eoCity));
			}
		}
	}

	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId
	 *            the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the cities
	 */
	public List<AddressCityVO> getCities() {
		return cities;
	}

	/**
	 * @param cities
	 *            the cities to set
	 */
	public void setCities(List<AddressCityVO> cities) {
		this.cities = cities;
	}

}
