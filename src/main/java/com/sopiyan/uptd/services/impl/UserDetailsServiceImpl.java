package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Sopiyan on 01/02/2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getOptional(email).orElseThrow(()->new UsernameNotFoundException(String.format("User dengan email %s tidak ditemukan", email)));
        return new CurrentUser(user);
    }
}
