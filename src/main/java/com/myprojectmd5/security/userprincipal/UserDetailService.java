package com.myprojectmd5.security.userprincipal;

import com.myprojectmd5.model.Users;
import com.myprojectmd5.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IAccountRepository accountRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users account = accountRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException( username+ " not found."));
        return UserPrincipal.build(account);
    }
}
