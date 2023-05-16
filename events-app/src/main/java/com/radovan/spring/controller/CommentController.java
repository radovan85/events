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

import com.radovan.spring.dto.CommentDto;
import com.radovan.spring.dto.ConsumerDto;
import com.radovan.spring.dto.EventDto;
import com.radovan.spring.dto.UserDto;
import com.radovan.spring.service.CommentService;
import com.radovan.spring.service.ConsumerService;
import com.radovan.spring.service.EventService;
import com.radovan.spring.service.UserService;

@Controller
@RequestMapping(value = "/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private UserService userService;

	@GetMapping(value="/allComments/{eventId}")
	public String getCommentsByEventId(@PathVariable("eventId") Integer eventId,ModelMap map) {
		List<CommentDto> allComments = commentService.listAllByEventId(eventId);
		EventDto event = eventService.getEventById(eventId);
		List<ConsumerDto> allConsumers = consumerService.listAll();
		List<UserDto> allUsers = userService.listAllUsers();
		map.put("allComments", allComments);
		map.put("event", event);
		map.put("allConsumers", allConsumers);
		map.put("allUsers", allUsers);
		map.put("recordsPerPage", 10);
		return "fragments/commentList :: ajaxLoadedContent";
	}
	
	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@GetMapping(value="/addComment/{eventId}")
	public String renderCommentForm(@PathVariable ("eventId") Integer eventId,ModelMap map) {
		CommentDto comment = new CommentDto();
		EventDto event = eventService.getEventById(eventId);
		map.put("comment", comment);
		map.put("event", event);
		return "fragments/commentForm :: ajaxLoadedContent";
	}
	
	@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	@PostMapping(value="/postComment")
	public String postComment(@ModelAttribute ("comment") CommentDto comment) {
		commentService.addComment(comment);
		return "fragments/homePage :: ajaxLoadedContent";
	}
	
	
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@GetMapping("/deleteComment/{commentId}/{eventId}")
    public String deleteComment(@PathVariable(name = "commentId") Integer commentId,
    		@PathVariable("eventId") Integer eventId) {
        commentService.deleteComment(commentId);
        return "fragments/homePage :: ajaxLoadedContent";
    }
	
	
}
