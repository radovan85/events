package com.radovan.spring.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class EventDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer eventId;

	private String eventTitle;

	private Timestamp eventDate;

	private String eventDateStr;

	private Integer locationId;

	private Integer categoryId;

	private List<Integer> commentsIds;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public Timestamp getEventDate() {
		return eventDate;
	}

	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDateStr() {
		return eventDateStr;
	}

	public void setEventDateStr(String eventDateStr) {
		this.eventDateStr = eventDateStr;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public List<Integer> getCommentsIds() {
		return commentsIds;
	}

	public void setCommentsIds(List<Integer> commentsIds) {
		this.commentsIds = commentsIds;
	}

}
