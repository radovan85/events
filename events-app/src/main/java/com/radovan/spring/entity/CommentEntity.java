package com.radovan.spring.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer commentId;
	
	@Column(nullable = false,length = 255)
	private String text;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private EventEntity event;

	@ManyToOne
	@JoinColumn(name = "consumer_id")
	private ConsumerEntity consumer;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public EventEntity getEvent() {
		return event;
	}

	public void setEvent(EventEntity event) {
		this.event = event;
	}

	public ConsumerEntity getConsumer() {
		return consumer;
	}

	public void setConsumer(ConsumerEntity consumer) {
		this.consumer = consumer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
