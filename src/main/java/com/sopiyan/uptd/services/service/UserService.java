package com.sopiyan.uptd.services.service;

import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.utils.enumeration.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


/**
 * Created by Sopiyan on 01/02/2017.
 */
public interface UserService {
    User simpan(User user);
    User perbarui(User user);
    void hapus(String idUser);
    Optional<User> getOptional(String email);
    User cariBerdasarkanEmail(String email);
    User cariBerdasarkanID(String id);
    Page<User> tampilkanSemua(Pageable page);
    Page<User> tampilkanBerdasarkanRole(Role role, Pageable page);
    Page<User> tampilkanBerdasarkanNamaLengkap(String namaLengkap, Pageable page);
    Long hitungJumlahUser();
}
