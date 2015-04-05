package com.healthcare.freemedicalopinion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.freemedicalopinion.manager.CountryManager;
import com.healthcare.freemedicalopinion.valueobject.AddressCountryVO;
import com.healthcare.freemedicalopinion.valueobject.CategoryManageReponseVO;

@Controller
public class AddressCountryController {

	@Autowired
	CountryManager countryManager;

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/viewcountry", method = RequestMethod.GET)
	public ModelAndView viewCategory() {
		return new ModelAndView("secure/managecountry");
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = { "/secure/getallcountries", "/getallcountries" }, method = RequestMethod.POST)
	public @ResponseBody List<AddressCountryVO> getAllCategories() {
		return countryManager.getAllCountry();
	}

	/**
	 * Only Admin
	 * */

	@RequestMapping(value = "/secure/manageCountry", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CategoryManageReponseVO manageCountry(
			@RequestBody AddressCountryVO vo) {

		CategoryManageReponseVO response = countryManager.manageCountry(vo);

		return response;
	}

	/**
	 * Only Admin
	 * */

	@RequestMapping(value = "/secure/manageCountryPopup", method = RequestMethod.GET)
	public ModelAndView getmanageCountryPopup() {
		return new ModelAndView("secure/manageaddresscountrypopup");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/manageState", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CategoryManageReponseVO manageState(
			@RequestBody AddressCountryVO vo) {

		CategoryManageReponseVO response = countryManager.manageState(vo);

		return response;
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/manageStatePopup", method = RequestMethod.GET)
	public ModelAndView getmanageStatePopup() {
		return new ModelAndView("secure/manageaddressstatepopup");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/manageCity", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CategoryManageReponseVO manageCity(
			@RequestBody AddressCountryVO vo) {

		CategoryManageReponseVO response = countryManager.manageCity(vo);

		return response;
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/manageCityPopup", method = RequestMethod.GET)
	public ModelAndView getmanageCityPopup() {
		return new ModelAndView("secure/manageaddresscitypopup");
	}

}
