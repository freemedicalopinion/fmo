package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import com.healthcare.freemedicalopinion.entityobject.ProcedureEO;

public interface ProcedureRepository extends
		BaseRepository<ProcedureEO, String> {

	List<ProcedureEO> findByProcedureName(String procedureName);

	List<ProcedureEO> findByProcedureId(String procedureId);

}
