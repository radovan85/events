package com.radovan.spring.service;

import java.util.List;

import com.radovan.spring.dto.ConsumerDto;
import com.radovan.spring.form.RegistrationForm;

public interface ConsumerService {

	ConsumerDto storeConsumer(RegistrationForm form);

	ConsumerDto getConsumerById(Integer consumerId);

	ConsumerDto getConsumerByUserId(Integer userId);

	void deleteConsumer(Integer consumerId);

	List<ConsumerDto> listAll();
}
