package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthcare.freemedicalopinion.entityobject.CommentEO;

@Repository
public interface CommentRepository extends BaseRepository<CommentEO, String> {

	List<CommentEO> findByContentId(String contentId);
	
}
