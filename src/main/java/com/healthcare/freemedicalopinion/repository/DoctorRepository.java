package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthcare.freemedicalopinion.entityobject.DoctorProfileEO;

@Repository
public interface DoctorRepository extends
		BaseRepository<DoctorProfileEO, String> {

	List<DoctorProfileEO> findByUsername(String username);

}
