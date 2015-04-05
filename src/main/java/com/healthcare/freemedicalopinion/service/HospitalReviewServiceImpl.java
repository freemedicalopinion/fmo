package com.healthcare.freemedicalopinion.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.HospitalReviewEO;
import com.healthcare.freemedicalopinion.repository.HospitalReviewRepository;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.HospitalReviewVO;

@Service
public class HospitalReviewServiceImpl implements HospitalReviewService {

	@Autowired
	HospitalReviewRepository reviewRepository;

	@Override
	public void addReview(HospitalReviewVO vo) {

		HospitalReviewEO eo = new HospitalReviewEO(vo);
		eo.setReviewId(String.valueOf(new Date().getTime()));
		eo.setUsername(UserUtility.getLoggedInUserUserName());
		eo.setCreatedDate(new Date());
		reviewRepository.save(eo);

	}

	@Override
	public List<HospitalReviewVO> getReviewsOfHospital(String hospitalId) {

		List<HospitalReviewEO> eos = reviewRepository
				.findByHospitalId(hospitalId);
		List<HospitalReviewVO> vos = new ArrayList<HospitalReviewVO>();
		if (eos != null) {
			for (HospitalReviewEO eo : eos) {
				vos.add(new HospitalReviewVO(eo));
			}
		}
		return vos;
	}

}
