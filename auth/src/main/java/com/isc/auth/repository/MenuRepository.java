package com.isc.auth.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.isc.auth.entitys.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer>{
	List<MenuEntity> findAllByActiveTrueOrderByOrderAsc();
	
	@Modifying
	@Transactional
	@Query("UPDATE MenuEntity u SET u.active=false, u.lastModificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = true")
	int inactive(@Param("id") Integer id);
	
    @Modifying
    @Transactional
    @Query("UPDATE MenuEntity u SET u.active = true, u.lastModificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = false")
    int active(@Param("id") Integer id);

	Set<MenuEntity> findByIdIn(Set<Integer> menusId);
}
