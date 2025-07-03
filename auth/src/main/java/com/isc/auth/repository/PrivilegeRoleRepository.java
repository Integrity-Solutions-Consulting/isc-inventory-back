package com.isc.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isc.auth.entitys.PrivilegeRoleEntity;

@Repository
public interface PrivilegeRoleRepository extends JpaRepository<PrivilegeRoleEntity, Integer>{
	List<PrivilegeRoleEntity> findByRoleIdAndActiveTrue(Integer usuarioId);
}
