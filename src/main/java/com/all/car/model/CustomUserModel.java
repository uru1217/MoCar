package com.all.car.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class CustomUserModel extends User {
    private int userId;
    private String name;
    private String email;

    public CustomUserModel(String username, String password, Collection<? extends GrantedAuthority> authorities, int userId, String name, String email) {
        super(username, password, authorities);
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
