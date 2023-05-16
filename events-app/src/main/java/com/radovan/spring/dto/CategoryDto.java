package com.radovan.spring.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer categoryId;

	private String title;

	private List<Integer> eventsIds;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Integer> getEventsIds() {
		return eventsIds;
	}

	public void setEventsIds(List<Integer> eventsIds) {
		this.eventsIds = eventsIds;
	}

}
