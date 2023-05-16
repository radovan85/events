package com.radovan.spring.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.radovan.spring.converter.TempConverter;
import com.radovan.spring.dto.PersistenceLoginDto;
import com.radovan.spring.entity.ConsumerEntity;
import com.radovan.spring.entity.PersistenceLoginEntity;
import com.radovan.spring.entity.UserEntity;
import com.radovan.spring.repository.ConsumerRepository;
import com.radovan.spring.repository.PersistenceLoginRepository;
import com.radovan.spring.service.PersistenceLoginService;

@Service
@Transactional
public class PersistenceLoginServiceImpl implements PersistenceLoginService {
	
	@Autowired
	private PersistenceLoginRepository persistenceRepository;
	
	@Autowired
	private TempConverter tempConverter;
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Override
	public PersistenceLoginDto addPersistenceLogin() {
		// TODO Auto-generated method stub
		
		PersistenceLoginDto returnValue = null;
		UserEntity authUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<ConsumerEntity> consumerOpt = Optional.ofNullable(consumerRepository.findByUserId(authUser.getId()));
		if(consumerOpt.isPresent()) {
			PersistenceLoginEntity persistence = new PersistenceLoginEntity();
			persistence.setConsumer(consumerOpt.get());
			Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
			persistence.setCreatedAt(createdAt);
			PersistenceLoginEntity storedPersistence = persistenceRepository.save(persistence);
			returnValue = tempConverter.persistenceLoginEntityToDto(storedPersistence);
		}
		
		return returnValue;
	}

	@Override
	public PersistenceLoginDto getLastLogin(Integer consumerId) {
		// TODO Auto-generated method stub
		PersistenceLoginDto returnValue = null;
		Optional<ConsumerEntity> consumerOpt = consumerRepository.findById(consumerId);
		if(consumerOpt.isPresent()) {
			Optional<PersistenceLoginEntity> persistence = 
					Optional.ofNullable(persistenceRepository.findLastLogin(consumerOpt.get().getConsumerId()));
			if(persistence.isPresent()) {
				returnValue = tempConverter.persistenceLoginEntityToDto(persistence.get());
			}
		}
		return returnValue;
	}

	@Override
	public void clearConsumerLogins(Integer consumerId) {
		// TODO Auto-generated method stub
		persistenceRepository.clearConsumerLogins(consumerId);
		persistenceRepository.flush();
	}
	
	
}
