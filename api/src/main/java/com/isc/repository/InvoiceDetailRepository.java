package com.isc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.entitys.InvoiceDetailEntity;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetailEntity, Integer>{
	List<InvoiceDetailEntity> findAllByStatusTrue();
	
	@Modifying
	@Transactional
	@Query("UPDATE InvoiceDetailEntity u SET u.status=false, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = true")
	int inactive(@Param("id") Integer id);
	
    @Modifying
    @Transactional
    @Query("UPDATE InvoiceDetailEntity u SET u.status = true, u.modificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.status = false")
    int active(@Param("id") Integer id);
}
