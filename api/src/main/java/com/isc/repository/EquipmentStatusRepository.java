package com.isc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.entitys.EquipmentStatusEntity;

@Repository
public interface EquipmentStatusRepository extends JpaRepository<EquipmentStatusEntity, Integer>{
	List<EquipmentStatusEntity> findAllByStatusTrue();
	Optional<EquipmentStatusEntity> findByName(String name);
	
	@Modifying
	@Transactional
	@Query("UPDATE EquipmentStatusEntity u SET u.status=false, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = true")
	int inactive(@Param("id") Integer id);
	
    @Modifying
    @Transactional
    @Query("UPDATE EquipmentStatusEntity u SET u.status = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = false")
    int active(@Param("id") Integer id);
}
