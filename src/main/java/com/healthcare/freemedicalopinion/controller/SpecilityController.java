package com.healthcare.freemedicalopinion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.freemedicalopinion.manager.SpecilityManager;
import com.healthcare.freemedicalopinion.valueobject.CategoryManageReponseVO;
import com.healthcare.freemedicalopinion.valueobject.SpecilityVO;

@Controller
public class SpecilityController {

	@Autowired
	SpecilityManager specilitymanager;

	/**
	 * Admin
	 * */
	@RequestMapping(value = "/secure/viewspecility", method = RequestMethod.GET)
	public ModelAndView viewSpecility() {
		return new ModelAndView("secure/managespecility");
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = { "/secure/getallspecilities", "/getallspecilities" }, method = RequestMethod.POST)
	public @ResponseBody List<SpecilityVO> getAllSpecilities() {
		List<SpecilityVO> specilities = specilitymanager.getAllSpecilites();
		return specilities;
	}
	/**
	 * Admin
	 * */
	@RequestMapping(value = "/secure/managespecility", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CategoryManageReponseVO manageSpecility(
			@RequestBody SpecilityVO speciltiy) {

		CategoryManageReponseVO vo = specilitymanager
				.manageSpecility(speciltiy);

		return vo;
	}
	/**
	 * Admin
	 * */
	@RequestMapping(value = "/secure/managespecilitypopup", method = RequestMethod.GET)
	public ModelAndView managespecilitypopup() {
		return new ModelAndView("secure/managespecilitypopup");
	}

}
