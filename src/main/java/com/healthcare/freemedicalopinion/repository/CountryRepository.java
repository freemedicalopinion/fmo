package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthcare.freemedicalopinion.entityobject.AddressCountryEO;

@Repository
public interface CountryRepository extends
		BaseRepository<AddressCountryEO, String> {

	List<AddressCountryEO> findByCountryName(String countryName);

	List<AddressCountryEO> findByCountryId(String countryId);

}
