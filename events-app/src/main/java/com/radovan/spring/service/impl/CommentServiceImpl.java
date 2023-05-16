package com.radovan.spring.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radovan.spring.converter.TempConverter;
import com.radovan.spring.dto.CommentDto;
import com.radovan.spring.entity.CommentEntity;
import com.radovan.spring.entity.ConsumerEntity;
import com.radovan.spring.entity.UserEntity;
import com.radovan.spring.exceptions.InvalidUserException;
import com.radovan.spring.repository.CommentRepository;
import com.radovan.spring.repository.ConsumerRepository;
import com.radovan.spring.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private TempConverter tempConverter;
	
	@Autowired
	private ConsumerRepository consumerRepository;

	@Override
	public CommentDto addComment(CommentDto comment) {
		// TODO Auto-generated method stub
		CommentDto returnValue = null;
		UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<ConsumerEntity> consumerOpt = Optional.ofNullable(consumerRepository.findByUserId(userEntity.getId()));
		if(consumerOpt.isPresent()) {
			comment.setConsumerId(consumerOpt.get().getConsumerId());
			CommentEntity commentEntity = tempConverter.commentDtoToEntity(comment);
			CommentEntity storedComment = commentRepository.save(commentEntity);
			returnValue = tempConverter.commentEntityToDto(storedComment);
		}else {
			Error error = new Error("Consumer Not Found!!!");
			throw new InvalidUserException(error);
		}
		
		return returnValue;
	}

	@Override
	public CommentDto getCommentById(Integer commentId) {
		// TODO Auto-generated method stub
		CommentDto returnValue = null;
		Optional<CommentEntity> commentOpt = commentRepository.findById(commentId);
		if (commentOpt.isPresent()) {
			returnValue = tempConverter.commentEntityToDto(commentOpt.get());
		}
		return returnValue;
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(commentId);
		commentRepository.flush();
	}

	@Override
	public List<CommentDto> listAll() {
		// TODO Auto-generated method stub
		List<CommentDto> returnValue = new ArrayList<>();
		Optional<List<CommentEntity>> allCommentsOpt = Optional.ofNullable(commentRepository.findAll());
		if (!allCommentsOpt.isEmpty()) {
			allCommentsOpt.get().forEach((comment) -> {
				CommentDto commentDto = tempConverter.commentEntityToDto(comment);
				returnValue.add(commentDto);
			});
		}
		return returnValue;
	}

	@Override
	public List<CommentDto> listAllByEventId(Integer eventId) {
		// TODO Auto-generated method stub
		List<CommentDto> returnValue = new ArrayList<>();
		Optional<List<CommentEntity>> allCommentsOpt = Optional.ofNullable(commentRepository.findAllByEventId(eventId));
		if (!allCommentsOpt.isEmpty()) {
			allCommentsOpt.get().forEach((comment) -> {
				CommentDto commentDto = tempConverter.commentEntityToDto(comment);
				returnValue.add(commentDto);
			});
		}
		return returnValue;
	}

	@Override
	public void removeAllByConsumerId(Integer consumerId) {
		// TODO Auto-generated method stub
		commentRepository.removeAllByConsumerId(consumerId);
		commentRepository.flush();
	}

}
