package com.isc.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import com.isc.api.entitys.EquipmentDismissalTypeEntity;

@Repository
public interface EquipmentDismissalTypeRepository extends JpaRepository<EquipmentDismissalTypeEntity, Integer> 
{

    Optional<EquipmentDismissalTypeEntity> findByIdAndStatusTrue(Integer id);

    List<EquipmentDismissalTypeEntity> findAllByStatusTrue();

    @Modifying
    @Transactional
    @Query("UPDATE EquipmentDismissalTypeEntity s SET s.status=false, s.modificationDate=CURRENT_TIMESTAMP WHERE s.id = :id AND s.status = true")
    int deactivate(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE EquipmentDismissalTypeEntity s SET s.status=true, s.modificationDate=CURRENT_TIMESTAMP WHERE s.id = :id AND s.status = false")
    int activate(@Param("id") Integer id);
}
