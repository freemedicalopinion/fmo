package com.healthcare.freemedicalopinion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.SpecilityEO;
import com.healthcare.freemedicalopinion.repository.SpecilityRepository;
import com.healthcare.freemedicalopinion.valueobject.SpecilityVO;

@Service
public class SpecilityServiceImpl implements SpeciltyService {

	@Autowired
	SpecilityRepository repository;

	@Override
	public List<SpecilityVO> getAllSpecilites() {
		List<SpecilityVO> vos = new ArrayList<SpecilityVO>();
		List<SpecilityEO> eos = repository.findAll();

		if (eos != null) {
			for (SpecilityEO eo : eos) {
				vos.add(new SpecilityVO(eo));
			}
		}
		return vos;
	}

	@Override
	public SpecilityVO getAllSpecilityBySpecilityName(String specilityName) {
		List<SpecilityEO> eos = repository.findBySpecilityName(specilityName);

		if (eos != null && eos.size() > 0) {
			return new SpecilityVO(eos.get(0));
		}
		return null;
	}

	@Override
	public SpecilityVO getAllSpecilityBySpecilityId(String specilityId) {

		List<SpecilityEO> eos = repository.findBySpecilityId(specilityId);
		if (eos != null && eos.size() > 0) {
			return new SpecilityVO(eos.get(0));
		}
		return null;
	}

	@Override
	public void addSpecility(SpecilityVO vo) {
		repository.save(new SpecilityEO(vo));

	}

	@Override
	public void editSecility(SpecilityVO vo) throws Exception {

		List<SpecilityEO> eos = repository.findBySpecilityId(vo
				.getSpecilityId());

		if (eos != null) {
			SpecilityEO eoTosave = new SpecilityEO(vo);
			SpecilityEO eo = eos.get(0);
			eo.setDescription(eoTosave.getDescription());
			eo.setSpecilityName(eoTosave.getSpecilityName());
			eo.setTags(eoTosave.getTags());
			repository.save(eo);

		}else{
			throw new Exception();
		}

	}

}
