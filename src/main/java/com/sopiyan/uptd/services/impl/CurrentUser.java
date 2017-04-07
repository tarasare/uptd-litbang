package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.utils.enumeration.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * Created by Sopiyan on 01/02/2017.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }
    public User getUser(){
        return this.user;
    }
    public String getEmail(){
        return this.user.getEmail();
    }
    public Role getRole(){
       return this.user.getRole();
    }
}
