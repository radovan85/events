package com.radovan.spring.form;

import java.io.Serializable;

import com.radovan.spring.dto.ConsumerDto;
import com.radovan.spring.dto.UserDto;

public class RegistrationForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDto user;

	private ConsumerDto consumer;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public ConsumerDto getConsumer() {
		return consumer;
	}

	public void setConsumer(ConsumerDto consumer) {
		this.consumer = consumer;
	}

}
