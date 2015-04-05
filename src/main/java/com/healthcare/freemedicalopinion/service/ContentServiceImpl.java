package com.healthcare.freemedicalopinion.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.ContentEO;
import com.healthcare.freemedicalopinion.repository.ContentRepository;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.ContentVO;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	ContentRepository contentRepository;

	@Override
	public void addContent(ContentVO vo) throws ParseException {
		ContentEO eo = new ContentEO(vo);
		eo.setContentId(String.valueOf(new Date().getTime()));
		eo.setAuthor(UserUtility.getLoggedInUserUserName());
		eo.setStatus(ContentStatus.NEW);
		contentRepository.save(eo);

	}

	@Override
	public List<ContentVO> getContentForLoggedInUser() {
		List<ContentEO> allContentForLoggedInUser = contentRepository
				.findByAuthor(UserUtility.getLoggedInUserUserName());

		List<ContentVO> allContentInVO = new ArrayList<ContentVO>();
		if (allContentForLoggedInUser != null) {
			for (ContentEO eo : allContentForLoggedInUser) {
				allContentInVO.add(new ContentVO(eo));
			}

		}

		return allContentInVO;
	}

	@Override
	public List<ContentVO> getAllContent() {
		List<ContentEO> allContentForLoggedInUser = contentRepository.findAll();

		List<ContentVO> allContentInVO = new ArrayList<ContentVO>();
		if (allContentForLoggedInUser != null) {
			for (ContentEO eo : allContentForLoggedInUser) {
				allContentInVO.add(new ContentVO(eo));
			}

		}

		return allContentInVO;
	}

	@Override
	public void editContent(ContentVO vo) throws ParseException {

		if (vo.getCategoryId() == null) {
			vo.setSubCategoryId(null);
		}
		ContentEO eoTosave = new ContentEO(vo);

		List<ContentEO> eos = contentRepository.findByContentId(vo
				.getContentId());
		if (eos != null && eos.size() > 0) {
			ContentEO eo = eos.get(0);
			if (eo.getStatus().equals(ContentStatus.APPROVED)) {
				eo.setParentContentId(eo.getId());
				eo.setContentId(String.valueOf(new Date().getTime()));
				eo.setStatus(ContentStatus.NEW);

				eo.setId(null);

			}

			eo.setTitle(eoTosave.getTitle());
			eo.setCategoryId(eoTosave.getCategoryId());
			eo.setSubCategoryId(eoTosave.getSubCategoryId());
			eo.setTags(eoTosave.getTags());
			eo.setImageName(eoTosave.getImageName());
			eo.setContentBody(eoTosave.getContentBody());
			eo.setUpdatedDate(new Date());
			contentRepository.save(eo);

		}

	}

	@Override
	public void deleteContent(String contentId) throws Exception {
		List<ContentEO> eos = contentRepository.findByContentId(contentId);
		if (eos != null && eos.size() > 0) {
			ContentEO eo = eos.get(0);
			if (!eo.getStatus().equals(ContentStatus.APPROVED)) {
				contentRepository.delete(eo);
			} else {
				throw new Exception();
			}
		}

	}

	@Override
	public void changeStatusOfContent(String contentId,
			ContentStatus finalstatus, String comment) throws Exception {
		List<ContentEO> eos = contentRepository.findByContentId(contentId);
		if (eos != null && eos.size() > 0) {
			ContentEO eo = eos.get(0);
			if (finalstatus.equals(ContentStatus.APPROVED)
					&& eo.getParentContentId() != null) {
				contentRepository.delete(eo);

				eo.setId(eo.getParentContentId());
				eo.setStatus(ContentStatus.APPROVED);
				eo.setParentContentId(null);
				eo.setUpdatedDate(new Date());
				contentRepository.save(eo);

			} else {
				eo.setStatus(finalstatus);
				eo.setComment(comment);
				eo.setUpdatedDate(new Date());
				contentRepository.save(eo);

			}
		}

	}

	@Override
	public ContentVO getContentBYContentId(String contentId) {
		if (contentId != null) {
			List<ContentEO> eos = contentRepository.findByContentIdAndStatus(contentId,ContentStatus.APPROVED);
			if (eos != null && eos.size() > 0) {
				return new ContentVO(eos.get(0));
			}
		}
		return null;
	}

	@Override
	public List<ContentVO> getLatestContentByCount(int pageNo, int size) {
		List<ContentEO> eos = contentRepository
				.findByStatusOrderByUpdatedDateAsc(ContentStatus.APPROVED,
						new PageRequest(pageNo, size, Sort.Direction.DESC,
								"updatedDate"));
		List<ContentVO> vos = new ArrayList<ContentVO>();
		if (eos != null) {
			for (ContentEO eo : eos) {
				vos.add(new ContentVO(eo));
			}
		}
		return vos;
	}

	@Override
	public long getCountOfContentByCategory(String category) {

		long count = contentRepository.countByCategoryId(category);
		return count;
	}

	@Override
	public List<ContentVO> getContentBYCategoryId(String categoryId, int page,
			int size) {
		List<ContentEO> eos = contentRepository
				.findByCategoryIdAndStatusOrderByUpdatedDateDesc(categoryId,
						ContentStatus.APPROVED, new PageRequest(page, size,
								Sort.Direction.DESC, "updatedDate"));

		List<ContentVO> vos = new ArrayList<ContentVO>();

		if (eos != null) {

			for (ContentEO eo : eos)
				vos.add(new ContentVO(eo));
		}
		return vos;
	}

	@Override
	public List<ContentVO> searchContentByKeyWord(String keyword) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(
				keyword);

		List<ContentEO> eos = contentRepository
				.findAllByOrderByScoreDesc(criteria);
		List<ContentVO> vos = new ArrayList<ContentVO>();

		if (eos != null) {
			for (ContentEO eo : eos) {
				vos.add(new ContentVO(eo));
			}

		}
		return vos;
	}

}
