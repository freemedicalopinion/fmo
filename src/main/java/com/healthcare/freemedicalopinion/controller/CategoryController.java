package com.healthcare.freemedicalopinion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.freemedicalopinion.manager.CategoryManager;
import com.healthcare.freemedicalopinion.valueobject.CategoryManageReponseVO;
import com.healthcare.freemedicalopinion.valueobject.CategoryVO;

@Controller
public class CategoryController {
	@Autowired
	CategoryManager categoryManager;

	/**
	 * Only Admin
	 * */
	
	@RequestMapping(value = "/secure/viewcategory", method = RequestMethod.GET)
	public ModelAndView viewCategory() {
		return new ModelAndView("secure/managecategory");
	}

	/**
	 * Common
	 * */
	@RequestMapping(value = { "/secure/getallcategories", "/getallcategories" }, method = RequestMethod.POST)
	public @ResponseBody List<CategoryVO> getAllCategories() {
		List<CategoryVO> categories = categoryManager.getAllCategories();
		return categories;
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/addcategory", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CategoryManageReponseVO manageCategory(
			@RequestBody CategoryVO category) {

		CategoryManageReponseVO vo = categoryManager.manageCategory(category);

		return vo;
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/getmanagecategorypopup", method = RequestMethod.GET)
	public ModelAndView getManageCategoryPopup() {
		return new ModelAndView("secure/managecategorypopup");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/getmanagesubcategorypopup", method = RequestMethod.GET)
	public ModelAndView getManageSubCategoryPopup() {
		return new ModelAndView("secure/managesubcategorypopup");
	}

	/**
	 * Only Admin
	 * */
	@RequestMapping(value = "/secure/manageSubcategory", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CategoryManageReponseVO manageSubCategory(
			@RequestBody CategoryVO category) {

		CategoryManageReponseVO vo = categoryManager
				.manageSubCategory(category);
		return vo;
	}

}
