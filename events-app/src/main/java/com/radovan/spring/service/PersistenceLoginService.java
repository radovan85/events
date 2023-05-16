package com.radovan.spring.service;

import com.radovan.spring.dto.PersistenceLoginDto;

public interface PersistenceLoginService {

	PersistenceLoginDto addPersistenceLogin();

	PersistenceLoginDto getLastLogin(Integer consumerId);

	void clearConsumerLogins(Integer consumerId);
}
