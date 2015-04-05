package com.healthcare.freemedicalopinion.service;

import java.text.ParseException;
import java.util.List;

import com.healthcare.freemedicalopinion.valueobject.CommentVO;

public interface CommentService {

	public List<CommentVO> getCommentByContentId(String contentId);

	public void addComment(CommentVO vo) throws ParseException;

}
