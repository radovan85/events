package com.radovan.spring.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.radovan.spring.dto.EventDto;
import com.radovan.spring.dto.LocationDto;
import com.radovan.spring.service.CategoryService;
import com.radovan.spring.service.EventService;
import com.radovan.spring.service.LocationService;

@Controller
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private LocationService locationService;

	@GetMapping(value = "/allEvents")
	public String getAllEvents(ModelMap map) {
		List<EventDto> allEvents = eventService.listAll();
		List<CategoryDto> allCategories = categoryService.listAll();
		List<LocationDto> allLocations = locationService.listAll();
		map.put("allEvents", allEvents);
		map.put("allCategories", allCategories);
		map.put("allLocations", allLocations);
		map.put("recordsPerPage", 5);
		return "fragments/eventList :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(value = "/addEvent")
	public String renderEventForm(ModelMap map) {
		EventDto event = new EventDto();
		List<CategoryDto> allCategories = categoryService.listAll();
		List<LocationDto> allLocations = locationService.listAll();
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime maxDate = today.plusDays(90);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String todayStr = today.format(formatter);
		String maxDateStr = maxDate.format(formatter);
		map.put("event", event);
		map.put("allCategories", allCategories);
		map.put("allLocations", allLocations);
		map.put("todayStr", todayStr);
		map.put("maxDateStr", maxDateStr);

		return "fragments/eventForm :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@PostMapping(value = "/storeEvent")
	public String storeEvent(@ModelAttribute("event") EventDto event) {
		eventService.addEvent(event);
		return "fragments/homePage :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(value = "/deleteEvent/{eventId}")
	public String deleteEvent(@PathVariable("eventId") Integer eventId) {
		eventService.deleteEvent(eventId);
		return "fragments/homePage :: ajaxLoadedContent";
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping(value = "/updateEvent/{eventId}")
	public String renderEventUpdateForm(@PathVariable("eventId") Integer eventId, ModelMap map) {
		EventDto event = new EventDto();
		EventDto currentEvent = eventService.getEventById(eventId);
		List<CategoryDto> allCategories = categoryService.listAll();
		List<LocationDto> allLocations = locationService.listAll();
		map.put("event", event);
		map.put("currentEvent", currentEvent);
		map.put("allCategories", allCategories);
		map.put("allLocations", allLocations);
		return "fragments/eventUpdateForm :: ajaxLoadedContent";
	}
	
	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@GetMapping(value = "/allEvents/{keyword}")
	public String searchEvents(@PathVariable ("keyword") String keyword,ModelMap map){
		List<EventDto> allEvents = eventService.listAllByKeyword(keyword);
		List<CategoryDto> allCategories = categoryService.listAll();
		List<LocationDto> allLocations = locationService.listAll();
		map.put("allEvents", allEvents);
		map.put("allCategories", allCategories);
		map.put("allLocations", allLocations);
		map.put("recordsPerPage", 5);
		return "fragments/eventSearchResult :: ajaxLoadedContent";
	}
}
