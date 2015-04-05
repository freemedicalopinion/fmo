package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.SpecilityEO;

public interface SpecilityRepository extends
		BaseRepository<SpecilityEO, String> {

	List<SpecilityEO> findBySpecilityName(String speciltiyName);

	List<SpecilityEO> findBySpecilityId(String specilityId);

}
