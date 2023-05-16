package com.radovan.spring.dto;

import java.io.Serializable;
import java.util.List;

public class LocationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer locationId;

	private String locationName;

	private String locationDetails;

	private String imageUrl;

	private List<Integer> eventsIds;

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Integer> getEventsIds() {
		return eventsIds;
	}

	public void setEventsIds(List<Integer> eventsIds) {
		this.eventsIds = eventsIds;
	}

}
