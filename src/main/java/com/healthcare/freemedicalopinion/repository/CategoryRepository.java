package com.healthcare.freemedicalopinion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthcare.freemedicalopinion.entityobject.CategoryEO;

@Repository
public interface CategoryRepository extends BaseRepository<CategoryEO, String> {

	List<CategoryEO> findByCategoryId(String categoryId);

	List<CategoryEO> findByCategoryIdAndNameAllIgnoreCase(String categoryId,String name);

	List<CategoryEO> findByNameIgnoreCase(String categoryName);


}
