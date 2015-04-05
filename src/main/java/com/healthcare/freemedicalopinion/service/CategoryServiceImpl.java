package com.healthcare.freemedicalopinion.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.healthcare.freemedicalopinion.entityobject.CategoryEO;
import com.healthcare.freemedicalopinion.entityobject.SubCategoryEO;
import com.healthcare.freemedicalopinion.repository.CategoryRepository;
import com.healthcare.freemedicalopinion.valueobject.CategoryVO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<CategoryVO> getAllCategories() {
		List<CategoryEO> categories = categoryRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		List<CategoryVO> categoriesVO = new ArrayList<CategoryVO>();
		if (categories != null) {
			for (CategoryEO eo : categories) {
				categoriesVO.add(new CategoryVO(eo));
			}
		}

		return categoriesVO;
	}

	@Override
	public void addCategory(CategoryVO vo) {
		if (vo != null) {
			categoryRepository.save(new CategoryEO(vo));
		}

	}

	@Override
	public void editCategory(CategoryVO vo) {
		if (vo != null) {
			CategoryEO eoToEdit = new CategoryEO(vo);

			List<CategoryEO> eoList = categoryRepository.findByCategoryId(vo
					.getCategoryId());
			if (eoList != null && eoList.size() > 0) {

				CategoryEO eoFromDb = eoList.get(0);
				eoFromDb.setDescription(eoToEdit.getDescription());
				eoFromDb.setName(eoToEdit.getName());
				eoFromDb.setTags(eoToEdit.getTags());
				eoFromDb.setImageName(eoToEdit.getImageName());
				categoryRepository.save(eoFromDb);

			}
		}

	}

	@Override
	public void addSubCategory(CategoryVO vo) {

		CategoryEO eoToEdit = new CategoryEO(vo);

		List<CategoryEO> eoList = categoryRepository.findByCategoryId(vo
				.getCategoryId());
		if (eoList != null && eoList.size() > 0) {

			CategoryEO eoFromDb = eoList.get(0);
			if (eoFromDb.getSubCategories() != null) {
				eoFromDb.getSubCategories().addAll(eoToEdit.getSubCategories());
			} else {
				eoFromDb.setSubCategories(eoToEdit.getSubCategories());
			}

			categoryRepository.save(eoFromDb);

		}

	}

	@Override
	public void editSubCategory(CategoryVO vo) throws Exception {

		CategoryEO eoToEdit = new CategoryEO(vo);

		SubCategoryEO subCategoryEO = eoToEdit.getSubCategories().get(0);

		List<CategoryEO> eoList = categoryRepository.findByCategoryId(vo
				.getCategoryId());

		if (eoList != null && eoList.size() > 0) {

			CategoryEO eoFromDb = eoList.get(0);

			List<SubCategoryEO> allEoFromDb = eoFromDb.getSubCategories();
			if (allEoFromDb != null && allEoFromDb.size() > 0) {
				Iterator<SubCategoryEO> itr = allEoFromDb.iterator();

				while (itr.hasNext()) {
					if (itr.next().getSubCategoryId()
							.equalsIgnoreCase(subCategoryEO.getSubCategoryId())) {
						itr.remove();
						break;

					}
				}
				allEoFromDb.add(subCategoryEO);
				categoryRepository.save(eoFromDb);

			} else {
				throw new Exception();
			}

		}

	}

	@Override
	public boolean checkIfCategoryNameUnique(String categoryId,
			String categoryName) {
		if (categoryName != null) {
			List<CategoryEO> categories = categoryRepository
					.findByNameIgnoreCase(categoryName);
			if (categories != null && categories.size() > 0) {

				CategoryEO eo = categories.get(0);

				if (eo.getCategoryId().equalsIgnoreCase(categoryId)) {
					return true;
				} else {
					return false;
				}

			}
		}
		return true;
	}

	@Override
	public boolean checkIfSubCategoryNameUnique(String categoryId,
			String subCategoryName, String subCategoryId) {

		if (categoryId != null && subCategoryName != null) {
			List<CategoryEO> categories = categoryRepository
					.findByCategoryId(categoryId);
			if (categories != null && categories.size() > 0) {

				for (CategoryEO eo : categories) {
					List<SubCategoryEO> subEos = eo.getSubCategories();
					if (subEos != null) {
						for (SubCategoryEO subEo : subEos) {
							if (subEo.getSubCategoryName().equalsIgnoreCase(
									subCategoryName)) {
								if (subCategoryId == null) {
									return false;
								} else if (!subEo.getSubCategoryId()
										.equalsIgnoreCase(subCategoryId)) {
									return false;
								}

							}
						}
					}
				}

			}
		}
		return true;
	}
}
