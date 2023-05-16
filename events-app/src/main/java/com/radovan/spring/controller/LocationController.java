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

import com.radovan.spring.dto.LocationDto;
import com.radovan.spring.service.EventService;
import com.radovan.spring.service.LocationService;

@Controller
@RequestMapping(value = "/locations")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private EventService eventService;

	@GetMapping(value = "/allLocations")
	public String getAllLocations(ModelMap map) {
		List<LocationDto> allLocations = locationService.listAll();
		map.put("allLocations", allLocations);
		map.put("recordsPerPage", 5);
		return "fragments/locationList :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(value = "/addLocation")
	public String renderLocationForm(ModelMap map) {
		LocationDto location = new LocationDto();
		map.put("location", location);
		return "fragments/locationForm :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@PostMapping(value = "/storeLocation")
	public String storeLocation(@ModelAttribute("location") LocationDto location) {
		locationService.addLocation(location);
		return "fragments/homePage :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(value = "/updateLocation/{locationId}")
	public String renderLocationUpdateForm(@PathVariable("locationId") Integer locationId, ModelMap map) {
		LocationDto location = new LocationDto();
		LocationDto currentLocation = locationService.getLocationById(locationId);
		map.put("location", location);
		map.put("currentLocation", currentLocation);
		return "fragments/locationUpdateForm :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(value = "/deleteLocation/{locationId}")
	public String deleteLocation(@PathVariable("locationId") Integer locationId) {
		LocationDto location = locationService.getLocationById(locationId);
		location.getEventsIds().forEach((eventId) -> {
			eventService.deleteEvent(eventId);
		});
		locationService.deleteLocation(locationId);
		return "fragments/homePage :: ajaxLoadedContent";
	}

}
