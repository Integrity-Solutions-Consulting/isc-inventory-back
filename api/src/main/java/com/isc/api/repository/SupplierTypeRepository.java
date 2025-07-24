package com.isc.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import com.isc.api.entitys.SupplierTypeEntity;

@Repository
public interface SupplierTypeRepository extends JpaRepository<SupplierTypeEntity, Integer> 
{

    List<SupplierTypeEntity> findAllByStatusTrue();

    @Modifying
    @Transactional
    @Query("UPDATE SupplierTypeEntity s SET s.status=false, s.modificationDate=CURRENT_TIMESTAMP WHERE s.id = :id AND s.status = true")
    int deactivate(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE SupplierTypeEntity s SET s.status=true, s.modificationDate=CURRENT_TIMESTAMP WHERE s.id = :id AND s.status = false")
    int activate(@Param("id") Integer id);
}
