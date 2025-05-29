package com.isc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.entitys.NationalityEntity;
import com.isc.entitys.PositionEntity;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Integer> {
	List<PositionEntity> findAllByActiveTrue();
	
	@Modifying
	@Transactional
	@Query("UPDATE PositionEntity u SET u.active=false, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = true")
	int inactive(@Param("id") Integer id);
	
    @Modifying
    @Transactional
    @Query("UPDATE PositionEntity u SET u.active = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = false")
    int active(@Param("id") Integer id);
}
