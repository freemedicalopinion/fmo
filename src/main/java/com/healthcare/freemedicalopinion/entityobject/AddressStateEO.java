package com.healthcare.freemedicalopinion.entityobject;

import java.util.ArrayList;
import java.util.List;

import com.healthcare.freemedicalopinion.valueobject.AddressCityVO;
import com.healthcare.freemedicalopinion.valueobject.AddressStateVO;

public class AddressStateEO {

	private String stateId;
	private String stateName;
	private List<AddressCityEO> cities;
	
	public AddressStateEO() {

	}

	public AddressStateEO(AddressStateVO vo) {
		this.stateId = vo.getStateId();
		this.stateName = vo.getStateName();
		if (vo.getCities() != null) {

			this.cities = new ArrayList<AddressCityEO>();

			for (AddressCityVO voCity : vo.getCities()) {
				this.cities.add(new AddressCityEO(voCity));
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
	public List<AddressCityEO> getCities() {
		return cities;
	}

	/**
	 * @param cities
	 *            the cities to set
	 */
	public void setCities(List<AddressCityEO> cities) {
		this.cities = cities;
	}

}
