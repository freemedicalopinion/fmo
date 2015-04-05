package com.healthcare.freemedicalopinion.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.service.SpeciltyService;
import com.healthcare.freemedicalopinion.utility.ReadValueFromMessageSource;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.CategoryManageReponseVO;
import com.healthcare.freemedicalopinion.valueobject.SpecilityVO;

@Component
public class SpecilityManager {

	@Autowired
	SpeciltyService specilityService;

	@Autowired
	ReadValueFromMessageSource message;

	public List<SpecilityVO> getAllSpecilites() {

		return specilityService.getAllSpecilites();

	}

	public SpecilityVO getAllSpecilityBySpecilityName(String specilityName) {
		return specilityService.getAllSpecilityBySpecilityName(specilityName);

	}

	public SpecilityVO getAllSpecilityBySpecilityId(String specilityId) {
		return specilityService.getAllSpecilityBySpecilityId(specilityId);

	}

	public CategoryManageReponseVO manageSpecility(SpecilityVO vo) {
		CategoryManageReponseVO response = new CategoryManageReponseVO();
		try {
			if (vo.getSpecilityName() == null
					|| vo.getSpecilityName().trim().equalsIgnoreCase("")) {
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
				response.setErrorMessage(message
						.readValueByKey("specility.namenotnull"));
				return response;

			}

			if (checkForUniqueName(vo)) {
				response.setUnique(true);
				if (vo.getSpecilityId() != null) {
					specilityService.editSecility(vo);
				} else {
					vo.setSpecilityId(String.valueOf(new Date().getTime()));
					specilityService.addSpecility(vo);
				}

			} else {
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
				response.setErrorMessage(message
						.readValueByKey("specility.namenotunique"));
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

	private boolean checkForUniqueName(SpecilityVO vo) {

		SpecilityVO vodb = specilityService.getAllSpecilityBySpecilityName(vo
				.getSpecilityName());

		if (vodb == null || vodb != null
				&& vodb.getSpecilityId().equalsIgnoreCase(vo.getSpecilityId())) {
			return true;

		}

		return false;
	}

}
