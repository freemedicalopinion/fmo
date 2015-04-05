package com.healthcare.freemedicalopinion.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.CommentEO;
import com.healthcare.freemedicalopinion.repository.CommentRepository;
import com.healthcare.freemedicalopinion.valueobject.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository repository;

	@Override
	public List<CommentVO> getCommentByContentId(String contentId) {
		List<CommentVO> vos = new ArrayList<CommentVO>();

		List<CommentEO> eos = repository.findByContentId(contentId);
		if (eos != null) {
			for (CommentEO eo : eos) {
				vos.add(new CommentVO(eo));
			}

		}

		return vos;
	}

	@Override
	public void addComment(CommentVO vo) throws ParseException {
		CommentEO eo = new CommentEO(vo);
		eo.setUpdated(new Date());
		repository.save(eo);

	}

}
