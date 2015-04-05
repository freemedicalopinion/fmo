package com.healthcare.freemedicalopinion.service;

import java.util.List;

import com.healthcare.freemedicalopinion.valueobject.CategoryVO;

public interface CategoryService {

	public List<CategoryVO> getAllCategories();

	public void addCategory(CategoryVO vo);

	public void editCategory(CategoryVO vo);

	public void addSubCategory(CategoryVO vo);

	public void editSubCategory(CategoryVO vo) throws Exception;

	public boolean checkIfCategoryNameUnique(String categoryId,
			String categoryName);

	public boolean checkIfSubCategoryNameUnique(String categoryId,
			String subCategoryName, String subCategoryId);

}
