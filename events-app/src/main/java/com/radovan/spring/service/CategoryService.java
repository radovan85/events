package com.radovan.spring.service;

import java.util.List;

import com.radovan.spring.dto.CategoryDto;

public interface CategoryService {

	CategoryDto addCategory(CategoryDto category);
	
	CategoryDto getCategoryById(Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	List<CategoryDto> listAll();
}
