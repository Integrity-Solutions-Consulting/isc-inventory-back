package com.isc.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.dto.response.EmployeeCatalogResponseDTO;
import com.isc.api.dto.response.EmployeeTableResponseDTO;
import com.isc.api.entitys.CustomerEntity;
import com.isc.api.entitys.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> 
{
	
	Optional<EmployeeEntity> findByIdentification(String identification);
	
	@Modifying
	@Transactional
	@Query("UPDATE EmployeeEntity u SET u.status=false, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = true")
	int inactive(@Param("id") Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE EmployeeEntity u SET u.status = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = false")
	int active(@Param("id") Integer id);

	@Query("SELECT new com.isc.api.dto.response.EmployeeCatalogResponseDTO(e.id, CONCAT(e.firstName, ' ', e.lastName),e.identification, e.email) FROM EmployeeEntity e WHERE e.status = true")
	List<EmployeeCatalogResponseDTO> findAllActiveEmployeeCatalog();

	@Query("""
			SELECT new com.isc.api.dto.response.EmployeeTableResponseDTO(
			    e.id,
			    e.firstName,
			    e.lastName,
			    e.position.name,
			    e.address,
			    e.email,
			    e.phone,
			    e.status,
			    e.identificationType.description,
			    e.identification,
			    e.gender.id,
			    e.workMode.id,
			    e.nationality.id,
			    e.contractDate,
			    e.contractEndDate
			)
			FROM EmployeeEntity e
			""")
			List<EmployeeTableResponseDTO> findAllEmployeeTableData();
}