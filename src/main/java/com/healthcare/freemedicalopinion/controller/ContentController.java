package com.healthcare.freemedicalopinion.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.freemedicalopinion.manager.ContentManager;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.ContentResponseVO;
import com.healthcare.freemedicalopinion.valueobject.ContentVO;
import com.healthcare.freemedicalopinion.valueobject.ContentValidateResponseVO;

@Controller
public class ContentController {

	@Autowired
	ContentManager contentManager;

	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure/getmanagecontentpopup", method = RequestMethod.GET)
	public ModelAndView getManageContentPopup() {

		return new ModelAndView("secure/managecontentpopup");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/managecontentapproval", method = RequestMethod.GET)
	public ModelAndView apprvalPageOfContent() {
		return new ModelAndView("secure/managecontentapproval");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/getcontentapprovalpopup", method = RequestMethod.GET)
	public ModelAndView getManageContentApprovalPopup() {
		return new ModelAndView("secure/managecontentapprovalpopup");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/getallcontentforapproval", method = RequestMethod.POST)
	public @ResponseBody ContentResponseVO getAllContent()
			throws ParseException {

		ContentResponseVO contentResponse = new ContentResponseVO();
		List<ContentVO> listOfContent = contentManager
				.getAllContentForLoggedInUser();

		contentResponse.setAllContent(listOfContent);
		contentResponse.setAllStatuses(Arrays.asList(
				ContentStatus.PENDING_APPROVAL, ContentStatus.REJECTED,
				ContentStatus.APPROVED));
		return contentResponse;
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure/manageContent", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ContentValidateResponseVO manageContent(
			@RequestBody ContentVO vo) {

		ContentValidateResponseVO response = contentManager.manageContent(vo);
		return response;
	}
	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure/deleteContent", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponseVO deleteContent(
			@RequestBody String contentId) {
		try {

			contentManager.deleteContent(contentId);
		} catch (Exception e) {
			return new BaseResponseVO(HTTP_RESPONSE.FAIL, e.getMessage());
		}
		return new BaseResponseVO(HTTP_RESPONSE.SUCCESS);
	}
	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure/publishContent", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ContentValidateResponseVO publishContent(
			@RequestBody ContentVO vo) {

		ContentValidateResponseVO response = contentManager.sendForApproval(vo);

		return response;
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/approveContent", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponseVO approveContent(@RequestBody ContentVO vo) {
		try {

			contentManager.approveContent(vo, true);
		} catch (Exception e) {
			return new BaseResponseVO(HTTP_RESPONSE.FAIL, e.getMessage());
		}
		return new BaseResponseVO(HTTP_RESPONSE.SUCCESS);
	}
	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/rejectContent", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BaseResponseVO rejectContent(@RequestBody ContentVO vo) {
		try {

			contentManager.approveContent(vo, false);
		} catch (Exception e) {
			return new BaseResponseVO(HTTP_RESPONSE.FAIL, e.getMessage());
		}
		return new BaseResponseVO(HTTP_RESPONSE.SUCCESS);
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure/managecontent", method = RequestMethod.GET)
	public ModelAndView viewCategory() {
		return new ModelAndView("secure/managecontent");
	}
	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure/getallcontent", method = RequestMethod.POST)
	public @ResponseBody ContentResponseVO getAllContentForUser()
			throws ParseException {
		ContentResponseVO contentResponse = new ContentResponseVO();
		List<ContentVO> listOfContent = contentManager
				.getAllContentForLoggedInUser();

		contentResponse.setAllContent(listOfContent);
		return contentResponse;
	}

	@RequestMapping(value = "/searchcontent", method = RequestMethod.GET)
	public ModelAndView searchcontent() {
		return new ModelAndView("public/articleSearch");
	}

	@RequestMapping(value = "/getSearchedContent", method = RequestMethod.POST)
	public @ResponseBody ContentResponseVO getSearchedContent(
			@RequestBody String keyword) {
		ContentResponseVO contentResponse = new ContentResponseVO();
		List<ContentVO> listOfContent = contentManager
				.getSearchedContent(keyword);

		contentResponse.setAllContent(listOfContent);
		return contentResponse;
	}

}
