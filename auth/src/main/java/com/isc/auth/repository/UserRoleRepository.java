package com.isc.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isc.auth.entitys.UserRoleEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer>{
	 List<UserRoleEntity> findByUserIdAndActiveTrue(Integer usuarioId);
}
