package com.healthcare.freemedicalopinion.manager;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.security.CustomUserDetails;
import com.healthcare.freemedicalopinion.service.CommentService;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.CommentVO;

@Component
public class CommentManager {

	@Autowired
	CommentService commentService;

	public List<CommentVO> getCommentByContentId(String contentId) {

		return commentService.getCommentByContentId(contentId);
	}

	public void addComment(CommentVO vo) throws ParseException {
		vo.setCommentId(String.valueOf(new Date().getTime()));
		vo.setUserId(UserUtility.getLoggedInUserUserName());
		CustomUserDetails ud = UserUtility.getLoggedInUser();
		if (ud != null) {
			vo.setFname(ud.getFname());
			vo.setLname(ud.getLname());
		}

		commentService.addComment(vo);

	}

}
