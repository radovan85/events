package com.radovan.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.radovan.spring.entity.PersistenceLoginEntity;

@Repository
public interface PersistenceLoginRepository extends JpaRepository<PersistenceLoginEntity, Integer> {

	@Query(value = "select * from persistence_logins where consumer_id = :consumerId order by created_at desc limit 1", nativeQuery = true)
	PersistenceLoginEntity findLastLogin(@Param("consumerId") Integer consumerId);

	@Modifying
	@Query(value = "delete from persistence_logins where consumer_id = :consumerId", nativeQuery = true)
	void clearConsumerLogins(@Param("consumerId") Integer consumerId);

}
