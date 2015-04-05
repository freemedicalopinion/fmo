package com.healthcare.freemedicalopinion.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.service.ProcedureService;
import com.healthcare.freemedicalopinion.utility.ReadValueFromMessageSource;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.CategoryManageReponseVO;
import com.healthcare.freemedicalopinion.valueobject.ProcedureVO;

@Component
public class ProcedureManager {

	@Autowired
	ProcedureService procedureService;

	@Autowired
	ReadValueFromMessageSource message;

	public List<ProcedureVO> getAllProcedures() {
		return procedureService.getAllProcedures();
	}

	public ProcedureVO getAllProcedureByProcedureName(String procedureName) {
		return procedureService.getAllProcedureByProcedureName(procedureName);

	}

	public ProcedureVO getAllProcedureByProcedureId(String procedureId) {
		return procedureService.getAllProcedureByProcedureId(procedureId);

	}

	public void addProcedure(ProcedureVO vo) {
		procedureService.addProcedure(vo);

	}

	public CategoryManageReponseVO manageProcedure(ProcedureVO vo) {
		CategoryManageReponseVO response = new CategoryManageReponseVO();
		try {
			if (vo.getProcedureName() == null
					|| vo.getProcedureName().trim().equalsIgnoreCase("")) {
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
				response.setErrorMessage(message
						.readValueByKey("procedure.namenotnull"));
				return response;

			}

			if (checkForUniqueName(vo)) {
				response.setUnique(true);
				if (vo.getProcedureId() != null) {
					procedureService.editProcedure(vo);
				} else {
					vo.setProcedureId(String.valueOf(new Date().getTime()));
					procedureService.addProcedure(vo);
				}

			} else {
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
				response.setErrorMessage(message
						.readValueByKey("procedure.namenotunique"));
				return response;
			}
			response.setResponseStatus(HTTP_RESPONSE.SUCCESS);
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.generalerror"));
			return response;
		}

		return response;
	}

	private boolean checkForUniqueName(ProcedureVO vo) {

		ProcedureVO vodb = procedureService.getAllProcedureByProcedureName(vo
				.getProcedureName());
		if (vodb == null || vodb != null
				&& vodb.getProcedureId().equalsIgnoreCase(vo.getProcedureId())) {
			return true;

		}

		return false;
	}
}
