package com.sopiyan.uptd.services.repositories;

import com.sopiyan.uptd.entities.entity.ResetPassword;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sopiyan on 10/02/2017.
 */
@Repository
public interface ResetPasswordRepository extends PagingAndSortingRepository<ResetPassword, String> {
    ResetPassword findOneByKodeResetAndUserEmail(String kodeReset, String email);
}
