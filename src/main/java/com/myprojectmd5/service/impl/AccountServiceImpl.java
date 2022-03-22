package com.myprojectmd5.service.impl;

import com.myprojectmd5.model.Users;
import com.myprojectmd5.repository.IAccountRepository;
import com.myprojectmd5.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    IAccountRepository accountRepository;

    @Override
    public Optional<Users> findByUsername(String name) {
        return accountRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public Users save(Users account) {
        return accountRepository.save(account);
    }
}
