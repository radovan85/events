package com.radovan.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.radovan.spring.entity.ConsumerEntity;

@Repository
public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Integer> {

	@Query(value = "select * from consumers where user_id = :userId", nativeQuery = true)
	ConsumerEntity findByUserId(@Param("userId") Integer userId);

}
