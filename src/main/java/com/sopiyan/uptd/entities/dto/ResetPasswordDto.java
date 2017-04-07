package com.sopiyan.uptd.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.util.Date;

/**
 * Created by Sopiyan on 08/02/2017.
 */
public class ResetPasswordDto {
    private String kodeReset;
    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern="dd/MM/yyyy HH:MM")
    private Date tglReset;
    private UserDto user;
    private String passwordBaru;
    private String passwordLama;

    public ResetPasswordDto() {
        tglReset = new Date();
    }

    public String getKodeReset() {
        return kodeReset;
    }

    public void setKodeReset(String kodeReset) {
        this.kodeReset = kodeReset;
    }

    public Date getTglReset() {
        return tglReset;
    }

    public void setTglReset(Date tglReset) {
        this.tglReset = tglReset;
    }

    public UserDto getUser() {
        return user;
    }

    public String getPasswordBaru() {
        return passwordBaru;
    }

    public void setPasswordBaru(String passwordBaru) {
        this.passwordBaru = passwordBaru;
    }

    public String getPasswordLama() {
        return passwordLama;
    }

    public void setPasswordLama(String passwordLama) {
        this.passwordLama = passwordLama;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
