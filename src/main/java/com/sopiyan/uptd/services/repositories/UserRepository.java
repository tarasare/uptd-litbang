package com.sopiyan.uptd.services.repositories;

import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.utils.enumeration.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sopiyan on 01/02/2017.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String>{
    User findOneByEmail(String email);
    Page<User> findByRoleOrderByNamaLengkap(Role role, Pageable page);
    Page<User> findByNamaLengkapContaining(String namaLengkap, Pageable page);
}
