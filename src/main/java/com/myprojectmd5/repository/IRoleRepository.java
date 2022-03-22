package com.myprojectmd5.repository;

import com.myprojectmd5.model.Role;
import com.myprojectmd5.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName (RoleName roleName);
}
