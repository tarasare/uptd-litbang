package com.sopiyan.uptd.services.service;

import com.sopiyan.uptd.entities.entity.ResetPassword;

/**
 * Created by Sopiyan on 10/02/2017.
 */
public interface ResetPasswordService {
    ResetPassword simpan(ResetPassword resetPassword);
    ResetPassword lihatBerdasarkanKodeResetDanEmailUser(String kodeReset, String email);
    void hapus(String id);
}
