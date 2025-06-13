package com.isc.auth.entitys;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(
    name = "users", 
    schema = "authentication",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "employeeId"),
        @UniqueConstraint(columnNames = "email")
    }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(
        name = "users_seq", 
        sequenceName = "authentication.users_id_seq", 
        allocationSize = 1
    )
    private Integer id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "text")
    private String password;

    @Column(name = "first_names", length = 100, nullable = false)
    private String firstNames;

    @Column(name = "last_connection")
    private LocalDateTime lastConnection;

    @Column(name = "is_logged_in", nullable = false)
    private boolean isLoggedIn = false;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private boolean suspended = false;

    @Column(name = "request_password_change", nullable = false)
    private boolean requestPasswordChange = false;

    @Column(name = "last_password_change_date")
    private LocalDateTime lastPasswordChangeDate = LocalDateTime.now();

    @Column(name = "failed_login_attempts", nullable = false)
    private int failedLoginAttempts = 0;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(length = 100)
    private String avatar = "http://172.16.14.5/media/profile/default-avatar.png";

    @Column(name = "last_modification_date")
    private LocalDateTime lastModificationDate;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<UserRoleEntity> userRoles = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<PrivilegeUserEntity> userPrivilegies = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<MenuUserEntity> userMenus = new HashSet<>();
}