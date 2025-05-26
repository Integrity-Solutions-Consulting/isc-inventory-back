package com.isc.auth.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.auth.entitys.RolesEntity;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Integer>{	
	List<RolesEntity> findAllByActiveTrue();

	Set<RolesEntity> findByIdIn(Set<Integer> ids);
	
	@Modifying
	@Transactional
	@Query("UPDATE RolesEntity u SET u.active=false WHERE u.id = :id AND u.active = true")
	int inactive(@Param("id") Integer id);
	
    @Modifying
    @Transactional
    @Query("UPDATE RolesEntity u SET u.active = true WHERE u.id = :id AND u.active = false")
    int active(@Param("id") Integer id);

}
