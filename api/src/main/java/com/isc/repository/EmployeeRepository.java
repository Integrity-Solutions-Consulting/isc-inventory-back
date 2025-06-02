package com.isc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isc.entitys.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}
