package com.healthcare.freemedicalopinion.service;

import java.util.List;

import com.healthcare.freemedicalopinion.valueobject.HospitalReviewVO;

public interface HospitalReviewService {

	void addReview(HospitalReviewVO vo);

	List<HospitalReviewVO> getReviewsOfHospital(String hospitalId);

}
