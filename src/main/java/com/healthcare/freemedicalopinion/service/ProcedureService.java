package com.healthcare.freemedicalopinion.service;

import java.util.List;

import com.healthcare.freemedicalopinion.valueobject.ProcedureVO;

public interface ProcedureService {

	public List<ProcedureVO> getAllProcedures();

	public ProcedureVO getAllProcedureByProcedureName(String procedureName);

	public ProcedureVO getAllProcedureByProcedureId(String procedureId);

	public void addProcedure(ProcedureVO vo);

	public void editProcedure(ProcedureVO vo) throws Exception;

}
