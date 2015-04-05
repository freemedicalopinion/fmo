package com.healthcare.freemedicalopinion.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.healthcare.freemedicalopinion.manager.CommentManager;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO;
import com.healthcare.freemedicalopinion.valueobject.CommentVO;

@Controller
public class CommentController {

	@Autowired
	CommentManager commentManager;

	@RequestMapping(value = "/getAllCommentForContent", method = RequestMethod.POST)
	public @ResponseBody List<CommentVO> getAllComment(
			@RequestBody String contentId) throws ParseException {

		return commentManager.getCommentByContentId(contentId);
	}

	@RequestMapping(value = "/secure/addComment", method = RequestMethod.POST)
	public @ResponseBody BaseResponseVO addComment(
			@RequestBody CommentVO comment) throws ParseException {
		commentManager.addComment(comment);
		return null;
	}

}
