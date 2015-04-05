package com.healthcare.freemedicalopinion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.ProcedureEO;
import com.healthcare.freemedicalopinion.repository.ProcedureRepository;
import com.healthcare.freemedicalopinion.valueobject.ProcedureVO;

@Service
public class ProcedureServiceImpl implements ProcedureService {

	@Autowired
	ProcedureRepository repository;

	@Override
	public List<ProcedureVO> getAllProcedures() {
		List<ProcedureVO> vos = new ArrayList<ProcedureVO>();
		List<ProcedureEO> eos = repository.findAll();

		if (eos != null) {
			for (ProcedureEO eo : eos) {
				vos.add(new ProcedureVO(eo));
			}
		}
		return vos;
	}

	@Override
	public ProcedureVO getAllProcedureByProcedureName(String procedureName) {
		List<ProcedureEO> eos = repository.findByProcedureName(procedureName);

		if (eos != null && eos.size() > 0) {
			return new ProcedureVO(eos.get(0));
		}
		return null;
	}

	@Override
	public ProcedureVO getAllProcedureByProcedureId(String procedureId) {
		List<ProcedureEO> eos = repository.findByProcedureId(procedureId);

		if (eos != null && eos.size() > 0) {
			return new ProcedureVO(eos.get(0));
		}
		return null;
	}

	@Override
	public void addProcedure(ProcedureVO vo) {
		repository.save(new ProcedureEO(vo));

	}

	@Override
	public void editProcedure(ProcedureVO vo) throws Exception {
		List<ProcedureEO> eos = repository.findByProcedureId(vo
				.getProcedureId());

		if (eos != null) {
			ProcedureEO eoTosave = new ProcedureEO(vo);
			ProcedureEO eo = eos.get(0);
			eo.setDescription(eoTosave.getDescription());
			eo.setProcedureName(eoTosave.getProcedureName());
			eo.setTags(eoTosave.getTags());
			repository.save(eo);

		} else {
			throw new Exception();
		}

	}

}
