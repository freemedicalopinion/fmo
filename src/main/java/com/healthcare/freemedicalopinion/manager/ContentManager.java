package com.healthcare.freemedicalopinion.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.service.ContentService;
import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.utility.FreeMedicalOpinionRoles;
import com.healthcare.freemedicalopinion.utility.ReadValueFromMessageSource;
import com.healthcare.freemedicalopinion.utility.UserUtility;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.ContentVO;
import com.healthcare.freemedicalopinion.valueobject.ContentValidateResponseVO;

@Component
public class ContentManager {

	@Autowired
	ContentService contentService;
	@Autowired
	ReadValueFromMessageSource message;

	public ContentValidateResponseVO manageContent(ContentVO vo) {

		ContentValidateResponseVO response = new ContentValidateResponseVO();

		try {

			if (vo != null && vo.getAuthor() != null
					&& !checkIfContentBelogToLoggedInUser(vo.getAuthor())) {
				response.setErrorMessage(message
						.readValueByKey("freemedicalopinion.unauthorisedaccess"));
				response.setResponseStatus(HTTP_RESPONSE.FAIL);

			} else {

				if (vo.getContentId() != null) {
					response = validateEditContent(vo);

					if (response.isValid()) {
						contentService.editContent(vo);
					}

				} else {
					contentService.addContent(vo);
				}
			}
		} catch (Exception e) {
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.generalerror"));
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
		}
		if (response.getResponseStatus() == null
				|| !response.getResponseStatus().equals(HTTP_RESPONSE.FAIL)) {
			response.setResponseStatus(HTTP_RESPONSE.SUCCESS);
		}
		return response;

	}

	public ContentValidateResponseVO deleteContent(String contentId)
			throws Exception {

		ContentValidateResponseVO response = new ContentValidateResponseVO();

		ContentVO vo = contentService.getContentBYContentId(contentId);

		if (vo != null && !checkIfContentBelogToLoggedInUser(vo.getAuthor())) {
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.unauthorisedaccess"));
			response.setResponseStatus(HTTP_RESPONSE.FAIL);

		} else {
			if (vo == null || vo.getStatus().equals(ContentStatus.APPROVED)) {
				response.setErrorMessage(message
						.readValueByKey("content.approvedtopendingapproval"));
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
			} else {

				contentService.deleteContent(contentId);
				response.setResponseStatus(HTTP_RESPONSE.SUCCESS);

			}
		}

		return response;
	}

	@Secured("ROLE_ADMIN")
	public List<ContentVO> getAllContent() {

		return contentService.getAllContent();

	}

	public List<ContentVO> getAllContentForLoggedInUser() {

		return contentService.getContentForLoggedInUser();

	}

	public ContentValidateResponseVO sendForApproval(ContentVO vo) {
		ContentValidateResponseVO response = new ContentValidateResponseVO();
		try {

			if (vo != null && vo.getAuthor() != null
					&& !checkIfContentBelogToLoggedInUser(vo.getAuthor())) {
				response.setErrorMessage(message
						.readValueByKey("freemedicalopinion.unauthorisedaccess"));
				response.setResponseStatus(HTTP_RESPONSE.FAIL);

			} else {

				if (vo.getStatus().equals(ContentStatus.APPROVED)) {
					response.setMessage(message
							.readValueByKey("content.approvedtopendingapproval"));
				} else {

					response = validateMandatoryFieldForContent(vo);

					if (response.isValid()) {
						List<String> roles = UserUtility
								.getLoggedInUserRoleList();
						if (roles != null
								&& roles.contains(FreeMedicalOpinionRoles.Role.ROLE_ADMIN)) {
							contentService.changeStatusOfContent(
									vo.getContentId(), ContentStatus.APPROVED,
									null);

						} else {
							contentService.changeStatusOfContent(
									vo.getContentId(),
									ContentStatus.PENDING_APPROVAL, null);
						}
					}
				}
			}
		} catch (Exception e) {
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.generalerror"));
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
		}
		if (response.getResponseStatus() == null
				|| !response.getResponseStatus().equals(HTTP_RESPONSE.FAIL)) {
			response.setResponseStatus(HTTP_RESPONSE.SUCCESS);
		}
		return response;

	}

	public void approveContent(ContentVO vo, boolean isApprove)
			throws Exception {
		ContentStatus sc = isApprove ? ContentStatus.APPROVED
				: ContentStatus.REJECTED;
		contentService.changeStatusOfContent(vo.getContentId(), sc,
				vo.getComment());

	}

	private ContentValidateResponseVO validateMandatoryFieldForContent(
			ContentVO vo) {

		ContentValidateResponseVO response = new ContentValidateResponseVO();
		String errorMessage = "";

		if (vo.getCategoryId() == null) {
			response.setCategoryId(true);
			errorMessage = errorMessage
					+ message.readValueByKey("content.categoryrequired")
					+ "<br>";
		}
		if (vo.getContentBody() == null) {
			response.setContentBody(true);
			errorMessage = errorMessage
					+ message.readValueByKey("content.bodyrequired") + "<br>";
		}
		if (vo.getSubCategoryId() == null || vo.getSubCategoryId().size() == 0) {
			response.setSubCategoryId(true);
			errorMessage = errorMessage
					+ message.readValueByKey("content.subcategoryrequired")
					+ "<br>";

		}
		if (vo.getTitle() == null) {
			response.setTitle(true);
			errorMessage = errorMessage
					+ message.readValueByKey("content.titlerequired") + "<br>";
		}
		response.setMessage(errorMessage);

		return response;
	}

	private ContentValidateResponseVO validateEditContent(ContentVO vo) {

		ContentVO dbContent = contentService.getContentBYContentId(vo
				.getContentId());

		ContentValidateResponseVO response = new ContentValidateResponseVO();

		if (dbContent.getStatus().equals(ContentStatus.PENDING_APPROVAL)
				|| dbContent.getStatus().equals(ContentStatus.REJECTED)) {

			response = validateMandatoryFieldForContent(vo);

		}

		return response;
	}

	private boolean checkIfContentBelogToLoggedInUser(String author) {

		if (UserUtility.getLoggedInUserUserName().equalsIgnoreCase(author)) {
			return true;
		}
		return false;
	}

	public List<ContentVO> getContentForHomePage() {
		return contentService.getLatestContentByCount(0, 11);
	}

	public long getCountOfContentByCategory(String categoryId) {
		return contentService.getCountOfContentByCategory(categoryId);
	}

	public List<ContentVO> getContentForCategoryPage(String categoryId, int page) {

		return contentService.getContentBYCategoryId(categoryId, page, 10);

	}

	public ContentVO getContentByContentId(String contentId) {

		return contentService.getContentBYContentId(contentId);

	}

	public List<ContentVO> getSearchedContent(String keyword) {
		
		return contentService.searchContentByKeyWord(keyword);
		
	}

}
