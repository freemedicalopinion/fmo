package com.healthcare.freemedicalopinion.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.HospitalProfileEO;
import com.healthcare.freemedicalopinion.repository.HospitalRepository;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.HospitalProfileVO;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepo;

	@Override
	public HospitalProfileVO getHospitalByHospitalId(String hospitalId) {
		List<HospitalProfileEO> eos = hospitalRepo.findByHospitalId(hospitalId);
		if (eos != null && eos.size() > 0) {
			return new HospitalProfileVO(eos.get(0));
		}
		return null;
	}

	@Override
	public void addHospital(HospitalProfileVO vo) {
		vo.setHospitalId(String.valueOf(new Date().getTime()));
		vo.setStatus(ContentStatus.NEW);
		hospitalRepo.save(new HospitalProfileEO(vo));

	}

	@Override
	public HospitalProfileVO getHospitalByHospitalName(String hospitalName) {
		List<HospitalProfileEO> eos = hospitalRepo
				.findByHospitalName(hospitalName);
		if (eos != null && eos.size() > 0) {
			return new HospitalProfileVO(eos.get(0));
		}
		return null;
	}

	@Override
	public HospitalProfileVO getHospitalByusername(String username) {
		List<HospitalProfileEO> eos = hospitalRepo.findByUsername(username);
		if (eos != null && eos.size() > 0) {
			return new HospitalProfileVO(eos.get(0));
		}
		return null;
	}

	@Override
	public void editHospital(HospitalProfileVO vo) {
		HospitalProfileEO eoTosave = new HospitalProfileEO(vo);

		List<HospitalProfileEO> eos = hospitalRepo.findByUsername(UserUtility
				.getLoggedInUserUserName());
		if (eos != null && eos.size() > 0) {
			eoTosave.setHospitalId(eos.get(0).getHospitalId());
			eoTosave.setUsername(eos.get(0).getUsername());
			eoTosave.setStatus(eos.get(0).getStatus());
			eoTosave.setId(eos.get(0).getId());
			eoTosave.setUpdatedDate(new Date());
			hospitalRepo.save(eoTosave);

		}

	}

	@Override
	public void chnageStatus(ContentStatus status) {
		List<HospitalProfileEO> eos = hospitalRepo.findByUsername(UserUtility
				.getLoggedInUserUserName());
		if (eos != null && eos.size() > 0) {

			HospitalProfileEO eo = eos.get(0);
			eo.setUpdatedDate(new Date());
			eo.setStatus(status);
			hospitalRepo.save(eo);

		}

	}

	@Override
	public List<HospitalProfileVO> getAllHospital() {
		List<HospitalProfileEO> eos = hospitalRepo.findAll();
		List<HospitalProfileVO> vos = new ArrayList<HospitalProfileVO>();
		if (eos != null && eos.size() > 0) {
			for (HospitalProfileEO eo : eos) {
				vos.add(new HospitalProfileVO(eo));
			}
		}

		return vos;
	}

	@Override
	public void chnageStatus(String hospitalId, ContentStatus status) {
		List<HospitalProfileEO> eos = hospitalRepo.findByHospitalId(hospitalId);
		if (eos != null && eos.size() > 0) {

			HospitalProfileEO eo = eos.get(0);
			eo.setStatus(status);
			eo.setUpdatedDate(new Date());
			hospitalRepo.save(eo);

		}

	}

	@Override
	public List<HospitalProfileVO> getLatestHospitalByCount(int pageNo, int size) {
		List<HospitalProfileEO> eos = hospitalRepo
				.findByStatusOrderByUpdatedDateAsc(ContentStatus.APPROVED,
						new PageRequest(pageNo, size, Sort.Direction.DESC,
								"updatedDate"));
		List<HospitalProfileVO> vos = new ArrayList<HospitalProfileVO>();
		if (eos != null) {
			for (HospitalProfileEO eo : eos) {
				vos.add(new HospitalProfileVO(eo));
			}
		}
		return vos;
	}

	@Override
	public HospitalProfileVO getHospitalByHospitalIdAndStatus(
			String hospitalId, ContentStatus status) {
		List<HospitalProfileEO> eos = hospitalRepo.findByHospitalIdAndStatus(
				hospitalId,status);
		if (eos != null && eos.size() > 0) {
			return new HospitalProfileVO(eos.get(0));
		}
		return null;
	}
}
