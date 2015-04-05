package com.healthcare.freemedicalopinion.service;

import java.util.List;

import com.healthcare.freemedicalopinion.valueobject.AddressCountryVO;

public interface CountryService {

	List<AddressCountryVO> getAllCountry();

	AddressCountryVO getCountryByCountryId(String countryId);

	AddressCountryVO getCountryByCountryName(String countryName);

	void addCountry(AddressCountryVO vo);

	void addState(AddressCountryVO vo);

	void addCity(AddressCountryVO vo);

	void editCountry(AddressCountryVO vo);

	void editState(AddressCountryVO vo);

	void editCity(AddressCountryVO vo);

}
