package com.healthcare.freemedicalopinion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.freemedicalopinion.manager.CategoryManager;
import com.healthcare.freemedicalopinion.manager.ContentManager;
import com.healthcare.freemedicalopinion.valueobject.ContentHomeResponseVO;
import com.healthcare.freemedicalopinion.valueobject.ContentVO;

@Controller
public class ContentFrontEndController {

	@Autowired
	CategoryManager categoryManager;
	@Autowired
	ContentManager contentManager;

	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public ModelAndView articleHomePageHTML() {
		ModelAndView m = new ModelAndView();
		m.setViewName("public/articlesHome");
		return m;
	}

	@RequestMapping(value = "/articles", method = RequestMethod.POST)
	public @ResponseBody ContentHomeResponseVO articleHomePage() {

		ContentHomeResponseVO response = new ContentHomeResponseVO();

		// List<CategoryVO> allCategories = categoryManager.getAllCategories();
		List<ContentVO> contents = contentManager.getContentForHomePage();
		if (contents == null) {
			contents = new ArrayList<ContentVO>();
		}
		response.setContents(contents);

		return response;
	}

	@RequestMapping(value = "/articles/category", method = RequestMethod.GET)
	public ModelAndView articleCategoryHomePage() {

		ModelAndView m = new ModelAndView();

		m.setViewName("public/articleCategoryHome");
		return m;
	}

	@RequestMapping(value = "/articles/category", method = RequestMethod.POST)
	public @ResponseBody ContentHomeResponseVO articleCategoryHomePageData(
			@RequestBody String category) {

		ContentHomeResponseVO response = new ContentHomeResponseVO();

		// List<CategoryVO> allCategories = categoryManager.getAllCategories();
		List<ContentVO> contents = contentManager.getContentForCategoryPage(
				category, 0);
		if (contents == null) {
			contents = new ArrayList<ContentVO>();
		}
		response.setContents(contents);

		return response;

	}

	@RequestMapping(value = "/articles/category/article", method = RequestMethod.GET)
	public ModelAndView articlePage() {
		return new ModelAndView("public/article");
	}

	@RequestMapping(value = "/articles/article", method = RequestMethod.POST)
	public @ResponseBody ContentHomeResponseVO articleData(
			@RequestBody String articleId) {

		ContentHomeResponseVO response = new ContentHomeResponseVO();

		// List<CategoryVO> allCategories = categoryManager.getAllCategories();
		ContentVO content = contentManager.getContentByContentId(articleId);
		List<ContentVO> contents = new ArrayList<ContentVO>();
		contents.add(content);
		response.setContents(contents);

		return response;

	}
}
