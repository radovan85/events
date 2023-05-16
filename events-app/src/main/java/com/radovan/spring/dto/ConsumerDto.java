package com.radovan.spring.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ConsumerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer consumerId;

	private Timestamp registrationDate;

	private String registrationDateStr;

	private Integer userId;

	private String avatarUrl;

	public Integer getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(Integer consumerId) {
		this.consumerId = consumerId;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationDateStr() {
		return registrationDateStr;
	}

	public void setRegistrationDateStr(String registrationDateStr) {
		this.registrationDateStr = registrationDateStr;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

}
