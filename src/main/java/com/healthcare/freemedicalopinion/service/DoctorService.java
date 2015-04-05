package com.healthcare.freemedicalopinion.service;

import com.healthcare.freemedicalopinion.valueobject.DoctorProfileVO;

public interface DoctorService {

	void addDoctor(DoctorProfileVO vo);
	
	DoctorProfileVO getDoctorByUsername(String username);

	void editDoctor(DoctorProfileVO vo);

}
