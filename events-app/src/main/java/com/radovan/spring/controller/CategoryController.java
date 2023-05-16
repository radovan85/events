package com.radovan.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.radovan.spring.dto.CategoryDto;
import com.radovan.spring.service.CategoryService;
import com.radovan.spring.service.EventService;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private EventService eventService;
	
	@GetMapping(value = "/allCategories")
	public String getAllCategories(ModelMap map) {
		List<CategoryDto> allCategories = categoryService.listAll();
		map.put("allCategories", allCategories);
		map.put("recordsPerPage", 10);
		return "fragments/categoryList :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(value = "/createCategory")
	public String renderCategoryForm(ModelMap map) {
		CategoryDto category = new CategoryDto();
		map.put("category", category);
		return "fragments/categoryForm :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@PostMapping(value = "/saveCategory")
	public String saveCategory(@ModelAttribute("category") CategoryDto category) {
		categoryService.addCategory(category);
		return "fragments/homePage :: ajaxLoadedContent";
	}
	
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(value="/updateCategory/{categoryId}")
	public String renderCategoryUpdateForm(@PathVariable ("categoryId") Integer categoryId,ModelMap map) {
		CategoryDto category = new CategoryDto();
		CategoryDto currentCategory = categoryService.getCategoryById(categoryId);
		map.put("category", category);
		map.put("currentCategory", currentCategory);
		return "fragments/categoryUpdateForm :: ajaxLoadedContent";
	}
	
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(value = "/deleteCategory/{categoryId}")
	public String deleteCategory(@PathVariable ("categoryId") Integer categoryId) {
		CategoryDto category = categoryService.getCategoryById(categoryId);
		category.getEventsIds().forEach((eventId) -> {
			eventService.deleteEvent(eventId);
		});
		categoryService.deleteCategory(categoryId);
		return "fragments/homePage :: ajaxLoadedContent";
	}
}
