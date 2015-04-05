package com.healthcare.freemedicalopinion.service;

import java.util.List;

import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.valueobject.HospitalProfileVO;

public interface HospitalService {

	HospitalProfileVO getHospitalByHospitalId(String hospitalId);

	HospitalProfileVO getHospitalByHospitalName(String hospitalName);

	void addHospital(HospitalProfileVO vo);

	HospitalProfileVO getHospitalByusername(String username);

	void editHospital(HospitalProfileVO vo);

	void chnageStatus(ContentStatus status);

	void chnageStatus(String hospitalId, ContentStatus status);

	List<HospitalProfileVO> getAllHospital();

	List<HospitalProfileVO> getLatestHospitalByCount(int pageNo, int size);

	HospitalProfileVO getHospitalByHospitalIdAndStatus(String hospitalId,
			ContentStatus status);

}
