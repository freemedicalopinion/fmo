package com.healthcare.freemedicalopinion.entityobject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.healthcare.freemedicalopinion.valueobject.AddressCountryVO;
import com.healthcare.freemedicalopinion.valueobject.AddressStateVO;

@Document(collection = "countryStateCity")
public class AddressCountryEO {

	@Id
	private String Id;

	private String countryId;

	private String countryName;

	private List<AddressStateEO> states;

	public AddressCountryEO() {

	}

	public AddressCountryEO(AddressCountryVO vo) {

		this.countryId = vo.getCountryId();
		this.countryName = vo.getCountryName();

		if (vo.getStates() != null) {

			this.states = new ArrayList<AddressStateEO>();

			for (AddressStateVO voState : vo.getStates()) {
				this.states.add(new AddressStateEO(voState));
			}
		}

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		Id = id;
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
	public List<AddressStateEO> getStates() {
		return states;
	}

	/**
	 * @param states
	 *            the states to set
	 */
	public void setStates(List<AddressStateEO> states) {
		this.states = states;
	}

}
