package com.radovan.spring.service;

import java.util.List;

import com.radovan.spring.dto.CommentDto;

public interface CommentService {

	CommentDto addComment(CommentDto comment);

	CommentDto getCommentById(Integer commentId);

	void deleteComment(Integer commentId);

	List<CommentDto> listAll();

	List<CommentDto> listAllByEventId(Integer eventId);
	
	void removeAllByConsumerId(Integer consumerId);
}
