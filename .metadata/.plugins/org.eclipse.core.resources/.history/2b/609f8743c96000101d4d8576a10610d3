package com.isc.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.entitys.AppearanceEntity;

@Repository
public interface AppearanceRepository extends JpaRepository<AppearanceEntity, Integer>{
	List<AppearanceEntity> findAllByActiveTrue();
	
	@Modifying
	@Transactional
	@Query("UPDATE AppearanceEntity u SET u.active=false, u.creationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = true")
	int inactive(@Param("id") Integer id);
	
    @Modifying
    @Transactional
    @Query("UPDATE AppearanceEntity u SET u.active = true, u.updatedAt = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = false")
    int active(@Param("id") Integer id);
}

