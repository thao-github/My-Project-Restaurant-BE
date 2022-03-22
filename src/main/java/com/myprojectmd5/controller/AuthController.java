package com.myprojectmd5.controller;

import com.myprojectmd5.dto.request.SignInForm;
import com.myprojectmd5.dto.request.SignUpForm;
import com.myprojectmd5.dto.response.JwtResponse;
import com.myprojectmd5.dto.response.ResponseMessage;
import com.myprojectmd5.model.Users;
import com.myprojectmd5.model.Role;
import com.myprojectmd5.model.RoleName;
import com.myprojectmd5.security.jwt.JwtProvider;
import com.myprojectmd5.security.userprincipal.UserPrincipal;
import com.myprojectmd5.service.impl.AccountServiceImpl;
import com.myprojectmd5.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (accountService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("The username existed."), HttpStatus.OK);
        }
        if (accountService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("The email existed."), HttpStatus.OK);
        }
        if (signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()) {
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/angular-3f534.appspot.com/o/user%20icon.png?alt=media&token=66500542-b353-406b-9160-e116d7030a5e");
        }
        Users account = new Users(
                signUpForm.getName(),
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                signUpForm.getAvatar(),
                passwordEncoder.encode(signUpForm.getPassword()));

        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "STORE_MANAGER":
                    Role adminRole = roleService.findByName(RoleName.STORE_MANAGER).orElseThrow(() ->
                            new RuntimeException("Role not found."));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRose = roleService.findByName(RoleName.STAFF).orElseThrow(() ->
                            new RuntimeException("Role not found."));
                    roles.add(userRose);
            }
        });
        account.setRoles(roles);
        accountService.save(account);
        return new ResponseEntity<>(new ResponseMessage("Create Account success."), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Users account = accountService.findByUsername(userPrincipal.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(token, account));

    }
}
