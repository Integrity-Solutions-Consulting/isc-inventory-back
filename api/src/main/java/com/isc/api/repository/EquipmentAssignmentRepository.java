package com.isc.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.entitys.EquipmentAssignmentEntity;

@Repository
public interface EquipmentAssignmentRepository extends JpaRepository<EquipmentAssignmentEntity, Integer> {
	List<EquipmentAssignmentEntity> findAllByStatusTrue();

	@Query("SELECT e FROM EquipmentAssignmentEntity e ORDER BY " + "CASE WHEN e.returnDate IS NULL THEN 0 ELSE 1 END, "
			+ "e.returnDate DESC")
	List<EquipmentAssignmentEntity> findAllOrderByReturnDateNullsFirst();

	@Modifying
	@Transactional
	@Query("UPDATE EquipmentAssignmentEntity u SET u.status=false, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = true")
	int inactive(@Param("id") Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE EquipmentAssignmentEntity u SET u.status = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = false")
	int active(@Param("id") Integer id);

	Optional<EquipmentAssignmentEntity> findTopByEquipment_IdOrderByAssignmentDateDesc(Integer equipmentId);
}
