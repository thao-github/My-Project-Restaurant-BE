package com.myprojectmd5.service.impl;

import com.myprojectmd5.model.Role;
import com.myprojectmd5.model.RoleName;
import com.myprojectmd5.repository.IRoleRepository;
import com.myprojectmd5.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
