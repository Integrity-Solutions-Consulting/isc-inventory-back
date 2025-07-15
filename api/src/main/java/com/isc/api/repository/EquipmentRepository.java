package com.isc.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.entitys.EquipmentEntity;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Integer> {
	List<EquipmentEntity> findAllByStatusTrue();
	Optional<EquipmentEntity> findBySerialNumber(String serialNumber);


	@EntityGraph(attributePaths = { "invoice", "condition", "equipStatus", "category", "warranty", "company",
			"characteristic" })
	List<EquipmentEntity> findAllByOrderByStatusDesc();

	@Modifying
	@Transactional
	@Query("UPDATE EquipmentEntity u SET u.status=false, u.equipStatus.id = 8, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = true")
	int inactive(@Param("id") Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE EquipmentEntity u SET u.status = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = false")
	int active(@Param("id") Integer id);

	boolean existsBySerialNumber(String serialNumber);

	boolean existsByItemCode(String itemCode);
	
	 @Query(value = "SELECT * FROM get_dashboard_totals()", nativeQuery = true)
	    List<Object[]> getDashboardTotalsRaw();

}
