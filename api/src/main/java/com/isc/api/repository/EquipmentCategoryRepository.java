package com.isc.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.entitys.EquipmentCategoryEntity;

@Repository
public interface EquipmentCategoryRepository extends JpaRepository<EquipmentCategoryEntity, Integer>
{
	List<EquipmentCategoryEntity> findAllByStatusTrue();
	
	@Modifying
	@Transactional
	@Query("UPDATE EquipmentCategoryEntity u SET u.status=false, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = true")
	int inactive(@Param("id") Integer id);
	
    @Modifying
    @Transactional
    @Query("UPDATE EquipmentCategoryEntity u SET u.status = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = false")
    int active(@Param("id") Integer id);
       
    @Transactional
    @Query("SELECT c FROM EquipmentCategoryEntity c LEFT JOIN FETCH c.stock WHERE c.id = :id")
    Optional<EquipmentCategoryEntity> findByIdWithStock(@Param("id") Integer id);
       
}
