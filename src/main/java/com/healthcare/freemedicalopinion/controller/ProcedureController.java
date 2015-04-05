package com.healthcare.freemedicalopinion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.freemedicalopinion.manager.ProcedureManager;
import com.healthcare.freemedicalopinion.valueobject.CategoryManageReponseVO;
import com.healthcare.freemedicalopinion.valueobject.ProcedureVO;

@Controller
public class ProcedureController {

	@Autowired
	ProcedureManager proceduremanager;

	/**
	 * Admin
	 * */
	@RequestMapping(value = "/secure/viewprocedure", method = RequestMethod.GET)
	public ModelAndView viewProcedure() {
		return new ModelAndView("secure/manageprocedure");
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = { "/secure/getallprocedures", "/getallprocedures" }, method = RequestMethod.POST)
	public @ResponseBody List<ProcedureVO> getallprocedures() {
		List<ProcedureVO> procedures = proceduremanager.getAllProcedures();
		return procedures;
	}
	/**
	 * Admin
	 * */
	@RequestMapping(value = "/secure/manageprocedure", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CategoryManageReponseVO manageProcedure(
			@RequestBody ProcedureVO procedure) {

		CategoryManageReponseVO vo = proceduremanager
				.manageProcedure(procedure);

		return vo;
	}
	/**
	 * Admin
	 * */
	@RequestMapping(value = "/secure/manageprocedurepopup", method = RequestMethod.GET)
	public ModelAndView managespecilitypopup() {
		return new ModelAndView("secure/manageprocedurepopup");
	}

}
