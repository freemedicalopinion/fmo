package com.healthcare.freemedicalopinion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.freemedicalopinion.manager.HospitalProfileManager;
import com.healthcare.freemedicalopinion.manager.HospitalReviewManager;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.HospitalProfileVO;
import com.healthcare.freemedicalopinion.valueobject.HospitalReviewVO;

@Controller
public class HospitalProfileController {

	@Autowired
	HospitalProfileManager hospitalManager;
	@Autowired
	HospitalReviewManager reviewManager;

	/**
	 * Hospital Only
	 * */

	@RequestMapping(value = "/secure/viewhospital", method = RequestMethod.GET)
	public ModelAndView viewCategory() {
		return new ModelAndView("secure/managehospitalprofile");
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = { "/secure/getallHospitalForApproval" }, method = RequestMethod.POST)
	public @ResponseBody List<HospitalProfileVO> getAllHospitalForApproval() {
		return hospitalManager.getAllHospitalForApproval();
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = { "/secure/getAllStatuses" }, method = RequestMethod.POST)
	public @ResponseBody ContentStatus[] getAllStatuses() {
		return ContentStatus.values();
	}

	/**
	 * Hospital
	 * */
	@RequestMapping(value = { "/secure/gethospitalInfoOfLoggedIn" }, method = RequestMethod.POST)
	public @ResponseBody HospitalProfileVO getHospitalInfoToEdit() {

		return hospitalManager.getHospitalForLoggedIn();

	}

	/**
	 * Hospital
	 * */
	@RequestMapping(value = "/secure/manageHospital", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponseVO managehospital(
			@RequestBody HospitalProfileVO hospital) {
		BaseResponseVO response = new BaseResponseVO(HTTP_RESPONSE.SUCCESS, "");
		try {
			hospitalManager.editHospital(hospital);
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage("Error occoured");

		}
		return response;
	}

	/**
	 * Hospital
	 * */
	@RequestMapping(value = "/secure/publishHospital", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponseVO publishHospital() {
		BaseResponseVO response = hospitalManager.publishHospital();
		return response;
	}

	/**
	 * Hospital
	 * */
	@RequestMapping(value = "/secure/getmanagehospitalpopup", method = RequestMethod.GET)
	public ModelAndView getManageHospitalPopup() {
		return new ModelAndView("secure/managehospitalprofilepopup");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/managehospitalapproval", method = RequestMethod.GET)
	public ModelAndView apprvalPageOfContent() {
		return new ModelAndView("secure/managehospitalapproval");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/getmanagehospitalapprovalpopup", method = RequestMethod.GET)
	public ModelAndView getManageContentApprovalPopup() {
		return new ModelAndView("secure/managehospitalapprovalpopup");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/approvehospital", method = RequestMethod.POST)
	public @ResponseBody BaseResponseVO approveHospital(
			@RequestBody String hospitalId) {
		return hospitalManager.approveHospital(hospitalId);
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/rejectHospital", method = RequestMethod.POST)
	public @ResponseBody BaseResponseVO rejectHospital(
			@RequestBody String hospitalId) {
		return hospitalManager.rejectHospital(hospitalId);
	}

	@RequestMapping(value = "/hospitals", method = RequestMethod.GET)
	public ModelAndView hospitalHomePage() {
		return new ModelAndView("public/hospitalHome");
	}

	@RequestMapping(value = "/hospital", method = RequestMethod.GET)
	public ModelAndView hospitalPage() {
		return new ModelAndView("public/hospital");
	}

	@RequestMapping(value = "/hospitalForhospitalHomepage", method = RequestMethod.POST)
	public @ResponseBody List<HospitalProfileVO> gethospitalForhospitalhomePage() {

		return hospitalManager.getHospitalForHospitalHomePage();
	}

	@RequestMapping(value = "/approvedHospital", method = RequestMethod.POST)
	public @ResponseBody HospitalProfileVO getApprovedhospitalbyId(
			@RequestBody String hospitalId) {

		return hospitalManager.getApprovedHospitalbyHospitalId(hospitalId);
	}

	@RequestMapping(value = "/addreview", method = RequestMethod.POST)
	public @ResponseBody BaseResponseVO addReview(
			@RequestBody HospitalReviewVO vo) {

		return reviewManager.addReview(vo);
	}

	@RequestMapping(value = "/getreviews", method = RequestMethod.POST)
	public @ResponseBody List<HospitalReviewVO> getReview(
			@RequestBody String hospitalId) {

		return reviewManager.getReviewsOfHospital(hospitalId);
	}

}
