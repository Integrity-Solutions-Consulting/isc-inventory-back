package com.isc.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.api.entitys.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>
{
	List<CustomerEntity> findAllByStatusTrue();
	
	Optional<CustomerEntity> findByRuc(String ruc);
	
	@Modifying
	@Transactional
	@Query("UPDATE CustomerEntity u SET u.status=false, u.modificationDate = CURRENT_TIMESTAMP WHERE u.ruc = :ruc AND u.status = true")
	int inactive(@Param("ruc") String ruc);
	
    @Modifying
    @Transactional
    @Query("UPDATE CustomerEntity u SET u.status = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.ruc = :ruc AND u.status = false")
    int active(@Param("ruc") String ruc);
}