package com.radovan.spring.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PersistenceLoginDto implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Timestamp createdAt;

	private String createdAtStr;

	private Integer consumerId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedAtStr() {
		return createdAtStr;
	}

	public void setCreatedAtStr(String createdAtStr) {
		this.createdAtStr = createdAtStr;
	}

	public Integer getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(Integer consumerId) {
		this.consumerId = consumerId;
	}

}
