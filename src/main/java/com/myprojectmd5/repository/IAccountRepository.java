package com.myprojectmd5.repository;

import com.myprojectmd5.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String name); //username co trong DB chua?

    Boolean existsByUsername(String username); //check xem username ton tai trong DB chua?

    Boolean existsByEmail(String email);//check xem email ton tai trong DB chua?
}
