package com.isc.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isc.auth.entitys.MenuUserEntity;

public interface MenuUserRepository extends JpaRepository<MenuUserEntity, Integer>{
	List<MenuUserEntity> findMenusByUserIdAndActiveTrue(Integer userId);
}
