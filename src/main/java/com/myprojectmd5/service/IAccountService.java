package com.myprojectmd5.service;

import com.myprojectmd5.model.Users;

import java.util.Optional;

public interface IAccountService {

    Optional<Users> findByUsername(String name); //username co trong DB chua?

    Boolean existsByUsername(String username); //check xem username ton tai trong DB chua?

    Boolean existsByEmail(String email);//check xem email ton tai trong DB chua?

    Users save(Users account);

}
