package com.isc.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isc.auth.entitys.PrivilegeUserEntity;

@Repository
public interface ProvilegeUserRepository extends JpaRepository<PrivilegeUserEntity, Integer>{
	List<PrivilegeUserEntity> findByUserIdAndActiveTrue(Integer usuarioId);
}
