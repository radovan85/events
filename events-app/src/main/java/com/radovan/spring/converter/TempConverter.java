package com.radovan.spring.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radovan.spring.dto.CategoryDto;
import com.radovan.spring.dto.CommentDto;
import com.radovan.spring.dto.ConsumerDto;
import com.radovan.spring.dto.EventDto;
import com.radovan.spring.dto.LocationDto;
import com.radovan.spring.dto.PersistenceLoginDto;
import com.radovan.spring.dto.RoleDto;
import com.radovan.spring.dto.UserDto;
import com.radovan.spring.entity.CategoryEntity;
import com.radovan.spring.entity.CommentEntity;
import com.radovan.spring.entity.ConsumerEntity;
import com.radovan.spring.entity.EventEntity;
import com.radovan.spring.entity.LocationEntity;
import com.radovan.spring.entity.PersistenceLoginEntity;
import com.radovan.spring.entity.RoleEntity;
import com.radovan.spring.entity.UserEntity;
import com.radovan.spring.repository.CategoryRepository;
import com.radovan.spring.repository.CommentRepository;
import com.radovan.spring.repository.ConsumerRepository;
import com.radovan.spring.repository.EventRepository;
import com.radovan.spring.repository.LocationRepository;
import com.radovan.spring.repository.RoleRepository;
import com.radovan.spring.repository.UserRepository;

@Component
public class TempConverter {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private RoleRepository roleRepository;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public CategoryDto categoryEntityToDto(CategoryEntity category) {
		CategoryDto returnValue = mapper.map(category, CategoryDto.class);
		Optional<List<EventEntity>> eventsOpt = Optional.ofNullable(category.getEvents());
		List<Integer> eventsIds = new ArrayList<>();
		if (!eventsOpt.isEmpty()) {
			eventsOpt.get().forEach((event) -> {
				eventsIds.add(event.getEventId());
			});
		}

		returnValue.setEventsIds(eventsIds);
		return returnValue;
	}

	public CategoryEntity categoryDtoToEntity(CategoryDto category) {
		CategoryEntity returnValue = mapper.map(category, CategoryEntity.class);
		Optional<List<Integer>> eventsIdsOpt = Optional.ofNullable(category.getEventsIds());
		List<EventEntity> events = new ArrayList<>();
		if (!eventsIdsOpt.isEmpty()) {
			eventsIdsOpt.get().forEach((eventId) -> {
				EventEntity event = eventRepository.findById(eventId).get();
				events.add(event);
			});
		}

		returnValue.setEvents(events);
		return returnValue;
	}

	public CommentDto commentEntityToDto(CommentEntity comment) {
		CommentDto returnValue = mapper.map(comment, CommentDto.class);

		Optional<Timestamp> createdAtOpt = Optional.ofNullable(comment.getCreatedAt());
		if (createdAtOpt.isPresent()) {
			String createdAtStr = createdAtOpt.get().toLocalDateTime().format(formatter);
			returnValue.setCreatedAtStr(createdAtStr);
		}

		Optional<EventEntity> eventOpt = Optional.ofNullable(comment.getEvent());
		if (eventOpt.isPresent()) {
			returnValue.setEventId(eventOpt.get().getEventId());
		}

		Optional<ConsumerEntity> consumerOpt = Optional.ofNullable(comment.getConsumer());
		if (consumerOpt.isPresent()) {
			returnValue.setConsumerId(consumerOpt.get().getConsumerId());
		}

		return returnValue;
	}

	public CommentEntity commentDtoToEntity(CommentDto comment) {
		CommentEntity returnValue = mapper.map(comment, CommentEntity.class);

		Optional<Integer> eventIdOpt = Optional.ofNullable(comment.getEventId());
		if (eventIdOpt.isPresent()) {
			Integer eventId = eventIdOpt.get();
			EventEntity event = eventRepository.findById(eventId).get();
			returnValue.setEvent(event);
		}

		Optional<Integer> consumerIdOpt = Optional.ofNullable(comment.getConsumerId());
		if (consumerIdOpt.isPresent()) {
			Integer consumerId = consumerIdOpt.get();
			ConsumerEntity consumer = consumerRepository.findById(consumerId).get();
			returnValue.setConsumer(consumer);
		}

		return returnValue;
	}

	public ConsumerDto consumerEntityToDto(ConsumerEntity consumer) {
		ConsumerDto returnValue = mapper.map(consumer, ConsumerDto.class);

		Optional<Timestamp> registrationDateOpt = Optional.ofNullable(consumer.getRegistrationDate());
		if (registrationDateOpt.isPresent()) {
			String registrationDateStr = registrationDateOpt.get().toLocalDateTime().format(formatter);
			returnValue.setRegistrationDateStr(registrationDateStr);
		}

		Optional<UserEntity> userOpt = Optional.ofNullable(consumer.getUser());
		if (userOpt.isPresent()) {
			returnValue.setUserId(userOpt.get().getId());
		}

		return returnValue;
	}

	public ConsumerEntity consumerDtoToEntity(ConsumerDto consumer) {
		ConsumerEntity returnValue = mapper.map(consumer, ConsumerEntity.class);

		Optional<Integer> userIdOpt = Optional.ofNullable(consumer.getUserId());
		if (userIdOpt.isPresent()) {
			Integer userId = userIdOpt.get();
			UserEntity user = userRepository.findById(userId).get();
			returnValue.setUser(user);
		}

		return returnValue;
	}

	public EventDto eventEntityToDto(EventEntity event) {
		EventDto returnValue = mapper.map(event, EventDto.class);

		Optional<Timestamp> eventDateOpt = Optional.ofNullable(event.getEventDate());
		if (eventDateOpt.isPresent()) {
			String eventDateStr = eventDateOpt.get().toLocalDateTime().format(formatter);
			returnValue.setEventDateStr(eventDateStr);
		}

		Optional<LocationEntity> locationOpt = Optional.ofNullable(event.getLocation());
		if (locationOpt.isPresent()) {
			returnValue.setLocationId(locationOpt.get().getLocationId());
		}

		Optional<CategoryEntity> categoryOpt = Optional.ofNullable(event.getCategory());
		if (categoryOpt.isPresent()) {
			returnValue.setCategoryId(categoryOpt.get().getCategoryId());
		}

		List<Integer> commentsIds = new ArrayList<>();
		Optional<List<CommentEntity>> commentsOpt = Optional.ofNullable(event.getComments());
		if (!commentsOpt.isEmpty()) {
			commentsOpt.get().forEach((comment) -> {
				commentsIds.add(comment.getCommentId());
			});
		}

		returnValue.setCommentsIds(commentsIds);
		return returnValue;
	}

	public EventEntity eventDtoToEntity(EventDto event) {
		EventEntity returnValue = mapper.map(event, EventEntity.class);

		Optional<Integer> locationIdOpt = Optional.ofNullable(event.getLocationId());
		if (locationIdOpt.isPresent()) {
			Integer locationId = locationIdOpt.get();
			LocationEntity location = locationRepository.findById(locationId).get();
			returnValue.setLocation(location);
		}

		Optional<Integer> categoryIdOpt = Optional.ofNullable(event.getCategoryId());
		if (categoryIdOpt.isPresent()) {
			Integer categoryId = categoryIdOpt.get();
			CategoryEntity category = categoryRepository.findById(categoryId).get();
			returnValue.setCategory(category);
		}

		Optional<String> eventDateStrOpt = Optional.ofNullable(event.getEventDateStr());
		if (eventDateStrOpt.isPresent()) {
			String eventDateStr = eventDateStrOpt.get();
			eventDateStr = eventDateStr.replace("T", " ");
			LocalDateTime eventDate = LocalDateTime.parse(eventDateStr, formatter);
			returnValue.setEventDate(Timestamp.valueOf(eventDate));
		}

		List<CommentEntity> comments = new ArrayList<>();
		Optional<List<Integer>> commentsIdsOpt = Optional.ofNullable(event.getCommentsIds());
		if (!commentsIdsOpt.isEmpty()) {
			commentsIdsOpt.get().forEach((commentId) -> {
				CommentEntity comment = commentRepository.findById(commentId).get();
				comments.add(comment);
			});
		}

		returnValue.setComments(comments);
		return returnValue;
	}

	public LocationDto locationEntityToDto(LocationEntity location) {
		LocationDto returnValue = mapper.map(location, LocationDto.class);

		List<Integer> eventsIds = new ArrayList<>();
		Optional<List<EventEntity>> eventsOpt = Optional.ofNullable(location.getEvents());
		if (!eventsOpt.isEmpty()) {
			eventsOpt.get().forEach((event) -> {
				eventsIds.add(event.getEventId());
			});
		}

		returnValue.setEventsIds(eventsIds);
		return returnValue;
	}

	public LocationEntity locationDtoToEntity(LocationDto location) {
		LocationEntity returnValue = mapper.map(location, LocationEntity.class);

		List<EventEntity> events = new ArrayList<>();
		Optional<List<Integer>> eventsIdsOpt = Optional.ofNullable(location.getEventsIds());
		if (!eventsIdsOpt.isEmpty()) {
			eventsIdsOpt.get().forEach((eventId) -> {
				EventEntity event = eventRepository.findById(eventId).get();
				events.add(event);
			});
		}

		returnValue.setEvents(events);
		return returnValue;
	}

	public PersistenceLoginDto persistenceLoginEntityToDto(PersistenceLoginEntity persistence) {
		PersistenceLoginDto returnValue = mapper.map(persistence, PersistenceLoginDto.class);

		Optional<Timestamp> createdAtOpt = Optional.ofNullable(persistence.getCreatedAt());
		if (createdAtOpt.isPresent()) {
			String createdAtStr = createdAtOpt.get().toLocalDateTime().format(formatter);
			returnValue.setCreatedAtStr(createdAtStr);
		}

		Optional<ConsumerEntity> consumerOpt = Optional.ofNullable(persistence.getConsumer());
		if (consumerOpt.isPresent()) {
			returnValue.setConsumerId(consumerOpt.get().getConsumerId());
		}

		return returnValue;
	}

	public PersistenceLoginEntity persistenceLoginDtoToEntity(PersistenceLoginDto persistence) {
		PersistenceLoginEntity returnValue = mapper.map(persistence, PersistenceLoginEntity.class);

		Optional<Integer> consumerIdOpt = Optional.ofNullable(persistence.getConsumerId());
		if (consumerIdOpt.isPresent()) {
			Integer consumerId = consumerIdOpt.get();
			ConsumerEntity consumer = consumerRepository.findById(consumerId).get();
			returnValue.setConsumer(consumer);
		}

		return returnValue;
	}

	public UserDto userEntityToDto(UserEntity userEntity) {
		UserDto returnValue = mapper.map(userEntity, UserDto.class);
		returnValue.setEnabled(userEntity.getEnabled());
		Optional<List<RoleEntity>> rolesOpt = Optional.ofNullable(userEntity.getRoles());
		List<Integer> rolesIds = new ArrayList<Integer>();

		if (!rolesOpt.isEmpty()) {
			rolesOpt.get().forEach((roleEntity) -> {
				rolesIds.add(roleEntity.getId());
			});
		}

		returnValue.setRolesIds(rolesIds);

		return returnValue;
	}

	public UserEntity userDtoToEntity(UserDto userDto) {
		UserEntity returnValue = mapper.map(userDto, UserEntity.class);
		List<RoleEntity> roles = new ArrayList<>();
		Optional<List<Integer>> rolesIdsOpt = Optional.ofNullable(userDto.getRolesIds());

		if (!rolesIdsOpt.isEmpty()) {
			rolesIdsOpt.get().forEach((roleId) -> {
				RoleEntity role = roleRepository.findById(roleId).get();
				roles.add(role);
			});
		}

		returnValue.setRoles(roles);

		return returnValue;
	}

	public RoleDto roleEntityToDto(RoleEntity roleEntity) {
		RoleDto returnValue = mapper.map(roleEntity, RoleDto.class);
		List<UserEntity> users = roleEntity.getUsers();
		List<Integer> userIds = new ArrayList<>();

		users.forEach((user) -> {
			userIds.add(user.getId());
		});

		returnValue.setUsersIds(userIds);
		return returnValue;
	}

	public RoleEntity roleDtoToEntity(RoleDto roleDto) {
		RoleEntity returnValue = mapper.map(roleDto, RoleEntity.class);
		List<Integer> usersIds = roleDto.getUsersIds();
		List<UserEntity> users = new ArrayList<>();

		usersIds.forEach((userId) -> {
			UserEntity userEntity = userRepository.findById(userId).get();
			users.add(userEntity);
		});

		returnValue.setUsers(users);
		return returnValue;
	}
}
