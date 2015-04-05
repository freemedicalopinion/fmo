package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthcare.freemedicalopinion.entityobject.HospitalReviewEO;

@Repository
public interface HospitalReviewRepository extends
		BaseRepository<HospitalReviewEO, String> {
	
	List<HospitalReviewEO> findByHospitalId(String hospitalId);

}
