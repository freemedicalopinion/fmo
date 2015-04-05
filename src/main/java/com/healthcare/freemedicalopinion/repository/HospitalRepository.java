package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.healthcare.freemedicalopinion.entityobject.HospitalProfileEO;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;

@Repository
public interface HospitalRepository extends
		BaseRepository<HospitalProfileEO, String> {

	List<HospitalProfileEO> findByUsername(String username);

	List<HospitalProfileEO> findByHospitalId(String hospitalId);

	List<HospitalProfileEO> findByHospitalIdAndStatus(String hospitalId,
			ContentStatus status);

	List<HospitalProfileEO> findByHospitalName(String hospitalName);

	List<HospitalProfileEO> findByStatusOrderByUpdatedDateAsc(
			ContentStatus status, Pageable page);

}
