package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.ResetPassword;
import com.sopiyan.uptd.services.repositories.ResetPasswordRepository;
import com.sopiyan.uptd.services.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sopiyan on 13/02/2017.
 */
@Service
public class ResetPasswordImpl implements ResetPasswordService {
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;
    @Override
    public ResetPassword simpan(ResetPassword resetPassword) {
        return resetPasswordRepository.save(resetPassword);
    }

    @Override
    public ResetPassword lihatBerdasarkanKodeResetDanEmailUser(String kodeReset, String email) {
        return resetPasswordRepository.findOneByKodeResetAndUserEmail(kodeReset, email);
    }

    @Override
    public void hapus(String id) {
        resetPasswordRepository.delete(id);
    }
}
