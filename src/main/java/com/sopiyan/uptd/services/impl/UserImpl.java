package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.repositories.UserRepository;
import com.sopiyan.uptd.services.service.UserService;
import com.sopiyan.uptd.utils.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Sopiyan on 01/02/2017.
 */
@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User simpan(User user) {
        return userRepository.save(user);
    }

    @Override
    public User perbarui(User user) {
        return userRepository.save(user);
    }

    @Override
    public void hapus(String idUser) {
        userRepository.delete(idUser);
    }

    @Override
    public Optional<User> getOptional(String email) {
        return Optional.ofNullable(userRepository.findOneByEmail(email));
    }

    @Override
    public Page<User> tampilkanSemua(Pageable page) {
        return userRepository.findAll(page);
    }

    @Override
    public Page<User> tampilkanBerdasarkanRole(Role role, Pageable page) {
        return userRepository.findByRoleOrderByNamaLengkap(role, page);
    }

    @Override
    public User cariBerdasarkanEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User cariBerdasarkanID(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public Long hitungJumlahUser() {
        return userRepository.count();
    }

    @Override
    public Page<User> tampilkanBerdasarkanNamaLengkap(String namaLengkap, Pageable page) {
        return userRepository.findByNamaLengkapContaining(namaLengkap, page);
    }
}
