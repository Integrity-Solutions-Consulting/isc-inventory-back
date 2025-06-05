package com.isc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.isc.entitys.WarrantTypeEntity;

public interface WarrantTypeRepository extends JpaRepository<WarrantTypeEntity, Integer>{
	List<WarrantTypeEntity> findAllByActiveTrue();
}
