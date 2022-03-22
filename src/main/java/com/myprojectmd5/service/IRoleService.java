package com.myprojectmd5.service;

import com.myprojectmd5.model.Role;
import com.myprojectmd5.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
