package com.healthcare.freemedicalopinion.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.service.HospitalReviewService;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.HospitalReviewVO;

@Component
public class HospitalReviewManager {

	@Autowired
	HospitalReviewService reviewservice;

	public BaseResponseVO addReview(HospitalReviewVO vo) {

		BaseResponseVO response = new BaseResponseVO(HTTP_RESPONSE.SUCCESS, "");

		if (UserUtility.checkIfUserLoggedIn()) {
			reviewservice.addReview(vo);
		} else {
			response = new BaseResponseVO(HTTP_RESPONSE.FAIL,
					"user not logged in");
		}

		return response;

	}

	public List<HospitalReviewVO> getReviewsOfHospital(String hospitalId) {
		return reviewservice.getReviewsOfHospital(hospitalId);
	}

}
