package com.healthcare.freemedicalopinion.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.freemedicalopinion.service.CategoryService;
import com.healthcare.freemedicalopinion.utility.ReadValueFromMessageSource;
import com.healthcare.freemedicalopinion.valueobject.BaseResponseVO.HTTP_RESPONSE;
import com.healthcare.freemedicalopinion.valueobject.CategoryManageReponseVO;
import com.healthcare.freemedicalopinion.valueobject.CategoryVO;
import com.healthcare.freemedicalopinion.valueobject.SubCategoryVO;

@Component
public class CategoryManager {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ReadValueFromMessageSource message;

	public List<CategoryVO> getAllCategories() {

		return categoryService.getAllCategories();

	}

	public CategoryManageReponseVO manageCategory(CategoryVO vo) {

		CategoryManageReponseVO response = new CategoryManageReponseVO();

		try {

			if (vo.getName() != null) {

				boolean isUnique = categoryService.checkIfCategoryNameUnique(
						vo.getCategoryId(), vo.getName());

				if (isUnique) {
					if (vo.getCategoryId() != null
							&& !vo.getCategoryId().equals("")) {
						categoryService.editCategory(vo);
					} else {
						vo.setCategoryId(String.valueOf(new Date().getTime()));
						categoryService.addCategory(vo);
					}

					response.setUnique(true);
				} else {

					response.setUnique(false);

					response.setMessage(message
							.readValueByKey("category.namenotunique"));

				}
			} else {
				response.setMessage(message
						.readValueByKey("category.namenotnull"));
				response.setUnique(false);
			}
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.generalerror"));
		}
		if (response.getResponseStatus() == null
				|| !response.getResponseStatus().equals(HTTP_RESPONSE.FAIL)) {
			response.setResponseStatus(HTTP_RESPONSE.SUCCESS);
		}

		return response;

	}

	public CategoryManageReponseVO manageSubCategory(CategoryVO vo) {

		CategoryManageReponseVO response = new CategoryManageReponseVO();
		boolean isUnique = false;

		try {
			if (vo.getCategoryId() != null && !vo.getCategoryId().equals("")
					&& vo.getSubCategories() != null
					&& vo.getSubCategories().size() > 0) {

				SubCategoryVO subCategory = vo.getSubCategories().get(0);

				if (subCategory.getSubCategoryName() != null
						&& !subCategory.getSubCategoryName().trim()
								.equalsIgnoreCase("")) {

					isUnique = checkIfSubCategoryNameUnique(vo);

					if (isUnique) {
						response.setUnique(true);

						if (subCategory.getSubCategoryId() != null
								&& !subCategory.getSubCategoryId().equals("")) {
							categoryService.editSubCategory(vo);
						} else {
							vo.getSubCategories()
									.get(0)
									.setSubCategoryId(
											String.valueOf(new Date().getTime()));
							categoryService.addSubCategory(vo);
						}
					} else {
						response.setUnique(false);
						response.setMessage(message
								.readValueByKey("subcategory.namenotunique"));
					}
				} else {
					response.setUnique(false);
					response.setMessage(message
							.readValueByKey("subcategory.namenotnull"));
				}

			} else {
				response.setResponseStatus(HTTP_RESPONSE.FAIL);
				response.setErrorMessage(message
						.readValueByKey("freemedicalopinion.generalerror"));
			}
		} catch (Exception e) {
			response.setResponseStatus(HTTP_RESPONSE.FAIL);
			response.setErrorMessage(message
					.readValueByKey("freemedicalopinion.generalerror"));
		}

		if (response.getResponseStatus() == null
				|| !response.getResponseStatus().equals(HTTP_RESPONSE.FAIL)) {
			response.setResponseStatus(HTTP_RESPONSE.SUCCESS);
		}

		return response;

	}

	private boolean checkIfSubCategoryNameUnique(CategoryVO category) {
		return categoryService.checkIfSubCategoryNameUnique(
				category.getCategoryId(), category.getSubCategories().get(0)
						.getSubCategoryName(),
				category.getSubCategories().get(0).getSubCategoryId());

	}
}
