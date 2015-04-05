package com.healthcare.freemedicalopinion.service;

import java.text.ParseException;
import java.util.List;

import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.valueobject.ContentVO;

public interface ContentService {

	public void addContent(ContentVO vo) throws ParseException;

	public List<ContentVO> getContentForLoggedInUser();

	public ContentVO getContentBYContentId(String contentId);

	public List<ContentVO> getContentBYCategoryId(String categoryId, int page,
			int size);

	public List<ContentVO> getAllContent();

	public void editContent(ContentVO vo) throws ParseException;

	public void deleteContent(String contentId) throws Exception;

	public void changeStatusOfContent(String contentId,
			ContentStatus finalstatus, String comment) throws Exception;

	public List<ContentVO> getLatestContentByCount(int pageNo, int size);

	public long getCountOfContentByCategory(String category);

	public List<ContentVO> searchContentByKeyWord(String keyword);

}
