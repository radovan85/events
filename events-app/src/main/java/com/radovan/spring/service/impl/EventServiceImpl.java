package com.radovan.spring.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radovan.spring.converter.TempConverter;
import com.radovan.spring.dto.EventDto;
import com.radovan.spring.entity.EventEntity;
import com.radovan.spring.repository.CommentRepository;
import com.radovan.spring.repository.EventRepository;
import com.radovan.spring.service.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private TempConverter tempConverter;

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public EventDto addEvent(EventDto event) {
		// TODO Auto-generated method stub
		EventEntity eventEntity = tempConverter.eventDtoToEntity(event);
		EventEntity storedEvent = eventRepository.save(eventEntity);
		EventDto returnValue = tempConverter.eventEntityToDto(storedEvent);
		return returnValue;
	}

	@Override
	public EventDto getEventById(Integer eventId) {
		// TODO Auto-generated method stub
		EventDto returnValue = null;
		Optional<EventEntity> eventOpt = eventRepository.findById(eventId);
		if (eventOpt.isPresent()) {
			returnValue = tempConverter.eventEntityToDto(eventOpt.get());
		}
		return returnValue;
	}

	@Override
	public void deleteEvent(Integer eventId) {
		// TODO Auto-generated method stub
		commentRepository.removeAllByEventId(eventId);
		eventRepository.removeEventById(eventId);
		eventRepository.flush();
	}

	@Override
	public List<EventDto> listAll() {
		// TODO Auto-generated method stub
		List<EventDto> returnValue = new ArrayList<>();
		Optional<List<EventEntity>> allEventsOpt = Optional.ofNullable(eventRepository.findAll());
		if (!allEventsOpt.isEmpty()) {
			allEventsOpt.get().forEach((event) -> {
				EventDto eventDto = tempConverter.eventEntityToDto(event);
				returnValue.add(eventDto);
			});
		}
		return returnValue;
	}

	@Override
	public List<EventDto> listAllByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<EventDto> returnValue = new ArrayList<>();
		Optional<List<EventEntity>> allEventsOpt = Optional.ofNullable(eventRepository.findAllByKeyword(keyword));
		if (!allEventsOpt.isEmpty()) {
			allEventsOpt.get().forEach((event) -> {
				EventDto eventDto = tempConverter.eventEntityToDto(event);
				returnValue.add(eventDto);
			});
		}
		return returnValue;
	}

}
