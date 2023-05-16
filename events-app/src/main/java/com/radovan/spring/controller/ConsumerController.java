package com.radovan.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.radovan.spring.dto.ConsumerDto;
import com.radovan.spring.dto.PersistenceLoginDto;
import com.radovan.spring.dto.UserDto;
import com.radovan.spring.service.CommentService;
import com.radovan.spring.service.ConsumerService;
import com.radovan.spring.service.PersistenceLoginService;
import com.radovan.spring.service.UserService;

@Controller
@RequestMapping(value = "/consumers")
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private UserService userService;

	@Autowired
	private PersistenceLoginService persistenceService;

	@Autowired
	private CommentService commentService;

	@GetMapping(value = "/allConsumers")
	public String getAllConsumers(ModelMap map) {
		List<UserDto> allUsers = userService.listAllUsers();
		List<ConsumerDto> allConsumers = consumerService.listAll();
		map.put("allUsers", allUsers);
		map.put("allConsumers", allConsumers);
		map.put("recordsPerPage", 10);
		return "fragments/consumerList :: ajaxLoadedContent";
	}

	@GetMapping(value = "/consumerDetails/{consumerId}")
	public String getConsumerDetails(@PathVariable("consumerId") Integer consumerId, ModelMap map) {
		ConsumerDto consumer = consumerService.getConsumerById(consumerId);
		UserDto user = userService.getUserById(consumer.getUserId());
		Optional<PersistenceLoginDto> lastLoginOpt = Optional.ofNullable(persistenceService.getLastLogin(consumerId));
		map.put("consumer", consumer);
		map.put("user", user);
		if (lastLoginOpt.isPresent()) {
			map.put("lastLoginStr", lastLoginOpt.get().getCreatedAtStr());
		} else {
			map.put("lastLoginStr", "Unknown");
		}

		return "fragments/consumerDetails :: ajaxLoadedContent";
	}

	@GetMapping(value = "/deleteConsumer/{consumerId}")
	public String deleteConsumer(@PathVariable("consumerId") Integer consumerId) {

		ConsumerDto consumer = consumerService.getConsumerById(consumerId);
		commentService.removeAllByConsumerId(consumerId);
		persistenceService.clearConsumerLogins(consumerId);
		consumerService.deleteConsumer(consumerId);
		userService.deleteUser(consumer.getUserId());
		return "fragments/homePage :: ajaxLoadedContent";
	}

	@GetMapping(value = "/suspendUser/{userId}")
	public String suspendUser(@PathVariable("userId") Integer userId) {
		userService.suspendUser(userId);
		return "fragments/homePage :: ajaxLoadedContent";
	}

	@GetMapping(value = "/reactivateUser/{userId}")
	public String reactivateUser(@PathVariable("userId") Integer userId) {
		userService.reactivateUser(userId);
		return "fragments/homePage :: ajaxLoadedContent";
	}

}
