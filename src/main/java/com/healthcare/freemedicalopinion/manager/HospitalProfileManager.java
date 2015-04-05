package com.healthcare.freemedicalopinion.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.service.HospitalService;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.HospitalProfileVO;
import com.healthcare.freemedicalopinion.valueobject.UserProfileVO;

@Component
public class HospitalProfileManager {

	@Autowired
	HospitalService hospitalService;

	public HospitalProfileVO getHospitalByHospitalId(String hospitalId) {
		return hospitalService.getHospitalByHospitalId(hospitalId);
	}

	public HospitalProfileVO getHospitalByHospitalName(String hospitalName) {
		return hospitalService.getHospitalByHospitalName(hospitalName);
	}

	public void upgradeToHospital(UserProfileVO vo) {
		hospitalService.addHospital(new HospitalProfileVO(vo));

	}

	public void editHospital(HospitalProfileVO vo) {
		hospitalService.editHospital(vo);

	}

	public HospitalProfileVO getHospitalForLoggedIn() {
		return hospitalService.getHospitalByusername(UserUtility
				.getLoggedInUserUserName());
	}

	public List<HospitalProfileVO> getAllHospitalForApproval() {
		return hospitalService.getAllHospital();
	}

	public BaseResponseVO publishHospital() {
		BaseResponseVO response = new BaseResponseVO();
		try {
			HospitalProfileVO vo = hospitalService
					.getHospitalByusername(UserUtility
							.getLoggedInUserUserName());
			response = validatePublish(vo);
			if (response.getResponseStatus().equals(HTTP_RESPONSE.FAIL)) {
				return response;
			} else {
				hospitalService.chnageStatus(ContentStatus.PENDING_APPROVAL);
			}
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage("Error");
		}
		return response;

	}

	public BaseResponseVO approveHospital(String hopitalId) {
		BaseResponseVO response = new BaseResponseVO(HTTP_RESPONSE.SUCCESS, "");
		try {
			hospitalService.chnageStatus(hopitalId, ContentStatus.APPROVED);
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage("Error");
		}
		return response;

	}

	public BaseResponseVO rejectHospital(String hopitalId) {
		BaseResponseVO response = new BaseResponseVO(HTTP_RESPONSE.SUCCESS, "");
		try {
			hospitalService.chnageStatus(hopitalId, ContentStatus.REJECTED);
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage("Error");
		}
		return response;

	}

	private BaseResponseVO validatePublish(HospitalProfileVO vo) {
		BaseResponseVO response = new BaseResponseVO();

		String errorMessage = "";

		if (vo.getHospitalName() == null
				|| vo.getHospitalName().trim().equalsIgnoreCase("")) {
			errorMessage = errorMessage + "Hospital Name is Null <br>";
		}
		if (vo.getAddress() == null) {
			errorMessage = errorMessage + "Hospital address  is Null <br>";
		}

		if (vo.getAddress() != null
				&& ((vo.getAddress().getAddress() == null || vo.getAddress()
						.getAddress().trim().equals("")))) {
			errorMessage = errorMessage + "Hospital address  is Null <br>";
		}
		if (vo.getAddress() != null
				&& ((vo.getAddress().getCountry() == null || vo.getAddress()
						.getCountry().trim().equals("")))) {
			errorMessage = errorMessage + "Hospital Country  is Null <br>";
		}
		if (vo.getAddress() != null
				&& (vo.getAddress().getState() == null || vo.getAddress()
						.getState().trim().equals(""))) {
			errorMessage = errorMessage + "Hospital State  is Null <br>";
		}
		if (vo.getAddress() != null
				&& (vo.getAddress().getCity() == null || vo.getAddress()
						.getCity().trim().equals(""))) {
			errorMessage = errorMessage + "Hospital City  is Null <br>";
		}

		if (errorMessage != "" && !errorMessage.equalsIgnoreCase("")) {
			response.setErrorMessage(errorMessage);
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
		} else {
			response.setResponseStatus(HTTP_RESPONSE.SUCCESS);
		}
		return response;
	}

	public List<HospitalProfileVO> getHospitalForHospitalHomePage() {
		return hospitalService.getLatestHospitalByCount(0, 6);
	}

	public HospitalProfileVO getApprovedHospitalbyHospitalId(String hospitalId) {
		return hospitalService.getHospitalByHospitalIdAndStatus(hospitalId,
				ContentStatus.APPROVED);
	}
}
