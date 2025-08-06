package com.isc.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.isc.api.entitys.EquipmentDismissalEntity;
import com.isc.api.entitys.EquipmentDismissalTypeEntity;

@Repository
public interface EquipmentDismissalRepository extends JpaRepository<EquipmentDismissalEntity, Integer> {
    
    List<EquipmentDismissalEntity> findAllByStatusTrue();
    
    List<EquipmentDismissalEntity> findByDismissalTypeId(Integer dismissalTypeId);
    
    List<EquipmentDismissalEntity> findByDismissalType(EquipmentDismissalTypeEntity dismissalType);
    
    List<EquipmentDismissalEntity> findByEquipmentId(Integer equipmentId);
    
    @Modifying
    @Transactional
    @Query("UPDATE EquipmentDismissalEntity u SET u.status=false, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = true")
    int inactive(@Param("id") Integer id);
    
    @Modifying
    @Transactional
    @Query("UPDATE EquipmentDismissalEntity u SET u.status = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = false")
    int active(@Param("id") Integer id);
}