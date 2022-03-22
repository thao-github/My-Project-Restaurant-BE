package com.myprojectmd5.security.userprincipal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myprojectmd5.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private Long id;
    private String name;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String avatar;
    private Collection<? extends GrantedAuthority> roles;

    public UserPrincipal() {
    }

    public UserPrincipal(Long id, String name, String username, String email, String password, String avatar, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.roles = roles;
    }

    public static UserPrincipal build(Users account) {
        List<GrantedAuthority> authorities = account.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
        return new UserPrincipal(
                account.getId(),
                account.getName(),
                account.getUsername(),
                account.getEmail(),
                account.getPassword(),
                account.getAvatar(),
                authorities
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//tra ve list quyen cua account
        return roles;
    }

    @Override
    public String getPassword() {//tra ve password da dung khi xac thuc
        return password;
    }

    @Override
    public String getUsername() {//tra ve username da dung khi xac thuc
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { //tra ve true khi account chua het han
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//tra ve true khi account chua bi lock
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//tra ve true khi password cua account chua het han
        return true;
    }

    @Override
    public boolean isEnabled() {//tra ve true neu account da duoc kich hoat
        return true;
    }
}
