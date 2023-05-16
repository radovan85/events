package com.radovan.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.radovan.spring.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

	@Query(value = "select * from comments where event_id = :eventId",nativeQuery = true)
	List<CommentEntity> findAllByEventId(@Param ("eventId") Integer eventId);

	@Modifying
	@Query(value = "delete from comments where consumer_id = :consumerId",nativeQuery = true)
	void removeAllByConsumerId(@Param ("consumerId") Integer consumerId);
	
	@Modifying
	@Query(value = "delete from comments where event_id = :eventId",nativeQuery = true)
	void removeAllByEventId(@Param ("eventId") Integer eventId);
}
