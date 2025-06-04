package com.isc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.dto.response.EmployeeCatalogResponseDTO;
import com.isc.dto.response.EmployeeTableResponseDTO;
import com.isc.entitys.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
	@Modifying
	@Transactional
	@Query("UPDATE EmployeeEntity u SET u.status=false, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = true")
	int inactive(@Param("id") Integer id);
	
    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity u SET u.status = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = false")
    int active(@Param("id") Integer id);
    
    @Query("SELECT new com.isc.dto.response.EmployeeCatalogResponseDTO(e.id, CONCAT(e.firstName, ' ', e.lastName)) FROM EmployeeEntity e WHERE e.status = true")
    List<EmployeeCatalogResponseDTO> findAllActiveEmployeeCatalog();
    
    @Query("SELECT new com.isc.dto.response.EmployeeTableResponseDTO(" +
            "e.id, " +
            "CONCAT(e.firstName, ' ', e.lastName), " +
            "e.position.name, " +
            "e.email, " +
            "e.phone, " +
            "e.status) " +
            "FROM EmployeeEntity e")
     List<EmployeeTableResponseDTO> findAllEmployeeTableData();
}
