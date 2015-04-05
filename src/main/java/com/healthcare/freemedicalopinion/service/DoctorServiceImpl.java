package com.healthcare.freemedicalopinion.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.DoctorProfileEO;
import com.healthcare.freemedicalopinion.repository.DoctorRepository;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.DoctorProfileVO;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepo;

	@Override
	public void addDoctor(DoctorProfileVO vo) {
		vo.setDoctorId(String.valueOf(new Date().getTime()));

		doctorRepo.save(new DoctorProfileEO(vo));

	}

	@Override
	public void editDoctor(DoctorProfileVO vo) {
		DoctorProfileEO eoToSave = new DoctorProfileEO(vo);
		DoctorProfileEO eo = null;
		List<DoctorProfileEO> eos = doctorRepo.findByUsername(UserUtility
				.getLoggedInUserUserName());
		if (eos != null && eos.size() > 0) {
			eo = eos.get(0);
		}
		eoToSave.setDoctorId(eo.getDoctorId());
		eoToSave.setUsername(eo.getUsername());
		eoToSave.setId(eo.getId());
		eoToSave.setUpdatedDate(new Date());
		doctorRepo.save(eoToSave);

	}

	@Override
	public DoctorProfileVO getDoctorByUsername(String username) {
		List<DoctorProfileEO> eos = doctorRepo.findByUsername(username);
		if (eos != null && eos.size() > 0) {
			return new DoctorProfileVO(eos.get(0));
		}

		return null;
	}

}
