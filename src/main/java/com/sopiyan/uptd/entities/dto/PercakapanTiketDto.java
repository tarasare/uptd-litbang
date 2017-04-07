package com.sopiyan.uptd.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.sopiyan.uptd.utils.enumeration.JenisPercakapan;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Sopiyan on 06/02/2017.
 */
public class PercakapanTiketDto {
    private String idPercakapan;
    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern="dd/MM/yyyy HH:MM")
    private Date tglPercakapan;
    @NotBlank(message = "Percakapan tidak boleh kosong")
    @Size(min = 3, max = 250, message = "Percakapan harus 3 - 250 karakter")
    private String isiPercakapan;
    private JenisPercakapan jenisPercakapan;
    private boolean checked;
    private UserDto user;
    private TiketDto tiket;

    public PercakapanTiketDto() {
        tglPercakapan = new Date();
    }

    public String getIdPercakapan() {
        return idPercakapan;
    }

    public void setIdPercakapan(String idPercakapan) {
        this.idPercakapan = idPercakapan;
    }

    public Date getTglPercakapan() {
        return tglPercakapan;
    }

    public void setTglPercakapan(Date tglPercakapan) {
        this.tglPercakapan = tglPercakapan;
    }

    public String getIsiPercakapan() {
        return isiPercakapan;
    }

    public void setIsiPercakapan(String isiPercakapan) {
        this.isiPercakapan = isiPercakapan;
    }

    public JenisPercakapan getJenisPercakapan() {
        return jenisPercakapan;
    }

    public void setJenisPercakapan(JenisPercakapan jenisPercakapan) {
        this.jenisPercakapan = jenisPercakapan;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public TiketDto getTiket() {
        return tiket;
    }

    public void setTiket(TiketDto tiket) {
        this.tiket = tiket;
    }
}
