package com.isc.auth.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.auth.entitys.PrivilegeEntity;
import com.isc.auth.entitys.RolesEntity;

@Repository
public interface PrivilegesRepository extends JpaRepository<PrivilegeEntity, Integer> {
	List<PrivilegeEntity> findAllByActiveTrue();
	
	Set<PrivilegeEntity> findByIdIn(Set<Integer> ids);

	@Modifying
	@Transactional
	@Query("UPDATE PrivilegeEntity u SET u.active=false, u.lastModificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = true")
	int inactivePrivilege(@Param("id") Integer id);
	
    @Modifying
    @Transactional
    @Query("UPDATE PrivilegeEntity u SET u.active = true, u.lastModificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = false")
    int activePrivilege(@Param("id") Integer id);
}
