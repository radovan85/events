package com.radovan.spring.service;

import java.util.List;

import com.radovan.spring.dto.EventDto;

public interface EventService {

	EventDto addEvent(EventDto event);

	EventDto getEventById(Integer eventId);

	void deleteEvent(Integer eventId);

	List<EventDto> listAll();

	List<EventDto> listAllByKeyword(String keyword);

}
