package com.radovan.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.radovan.spring.entity.EventEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer> {

	@Query(value = "select * from events where event_title ilike concat('%', :keyword, '%')",nativeQuery = true)
	List<EventEntity> findAllByKeyword(@Param("keyword") String keyword);

	@Modifying
	@Query(value = "delete from events where id = :eventId",nativeQuery = true)
	void removeEventById(@Param ("eventId") Integer eventId);
	
}
