package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;

import com.healthcare.freemedicalopinion.entityobject.ContentEO;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;

public interface ContentRepository extends BaseRepository<ContentEO, String> {

	List<ContentEO> findByAuthor(String author);

	List<ContentEO> findByContentIdAndStatus(String contentId, ContentStatus status);
	
	List<ContentEO> findByContentId(String contentId);

	List<ContentEO> findByCategoryIdAndStatusOrderByUpdatedDateDesc(
			String categoryId, ContentStatus status, Pageable page);

	List<ContentEO> findAllByOrderByScoreDesc(TextCriteria criteria);

	List<ContentEO> findByStatusOrderByUpdatedDateAsc(ContentStatus status,
			Pageable page);

	public Long countByCategoryId(String cat);

}
