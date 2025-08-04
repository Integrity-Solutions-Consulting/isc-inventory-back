package com.isc.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isc.auth.entitys.PrivilegeEntity;
import com.isc.auth.entitys.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	List<PrivilegeEntity> findAllByActiveTrue();

	Optional<UserEntity> findByUsername(String username);

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUsernameAndActiveTrueAndSuspendedFalse(String username);

	@EntityGraph(attributePaths = { "userRoles", "userRoles.role", "userRoles.role.rolesPrivilegies",
			"userRoles.role.rolesPrivilegies.privilege", "userPrivilegies", "userPrivilegies.privilege" })
	Optional<UserEntity> findByEmailAndActiveTrueAndSuspendedFalse(String email);

	@Query("""
			SELECT DISTINCT u FROM UserEntity u
			LEFT JOIN FETCH u.userRoles ur
			LEFT JOIN FETCH ur.role r
			""")
	List<UserEntity> findAllWithRolesAndPrivileges();
	
	@Query("""
		    SELECT DISTINCT u FROM UserEntity u
		    LEFT JOIN FETCH u.userRoles ur
		    LEFT JOIN FETCH ur.role r
		    LEFT JOIN FETCH u.userPrivilegies up
		    LEFT JOIN FETCH up.privilege p
		    LEFT JOIN FETCH u.userMenus um
		    LEFT JOIN FETCH um.menu m
		    """)
		List<UserEntity> findAllWithRolesPrivilegesAndMenus();


	@Modifying
	@Transactional
	@Query("UPDATE UserEntity u SET u.active=false, u.lastModificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = true")
	int softDelete(@Param("id") Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE UserEntity u SET u.active = true, u.lastModificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.active = false")
	int activateUser(@Param("id") Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE UserEntity u SET u.suspended=true, u.lastModificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.suspended = false")
	int suspendUser(@Param("id") Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE UserEntity u SET u.suspended=false, u.lastModificationDate = CURRENT_TIMESTAMP WHERE u.id = :id AND u.suspended = true")
	int unsuspendUser(@Param("id") Integer id);

}
