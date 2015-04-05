package com.healthcare.freemedicalopinion.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.service.DoctorService;
import com.healthcare.freemedicalopinion.valueobject.DoctorProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

@Component
public class DoctorProfileManager {
	@Autowired
	DoctorService doctorService;

	public void addDoctor(UserProfileVO vo) {

		doctorService.addDoctor(new DoctorProfileVO(vo));

	}

	public void editDoctor(DoctorProfileVO vo) {
		doctorService.editDoctor(vo);
	}

}
