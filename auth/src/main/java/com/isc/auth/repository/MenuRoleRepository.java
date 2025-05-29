package com.isc.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isc.auth.entitys.MenuRoleEntity;

public interface MenuRoleRepository extends JpaRepository<MenuRoleEntity, Integer> {

	List<MenuRoleEntity> findByRoleIdAndActiveTrue(Integer roleId);

}
