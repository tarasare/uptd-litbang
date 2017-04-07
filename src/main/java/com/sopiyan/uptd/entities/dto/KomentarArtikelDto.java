package com.sopiyan.uptd.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by sopiyan on 16/03/2017.
 */
public class KomentarArtikelDto {
    private String idKomentar;
    private UserDto user;
    @NotNull(message = "Artitel tidak boleh kosong")
    private ArtikelDto artikel;
    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern="hh:mm:ss dd MMMM yyyy")
    private Date tglKomentar;
    @NotBlank(message = "Komentar Tidak boleh kosong")
    @Size(min = 20, max = 250, message = "Komentar minimal 20 karakter dan maksimal 250")
    private String isiKomentar;

    public KomentarArtikelDto() {
        tglKomentar = new Date();
    }

    public String getIdKomentar() {
        return idKomentar;
    }

    public void setIdKomentar(String idKomentar) {
        this.idKomentar = idKomentar;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ArtikelDto getArtikel() {
        return artikel;
    }

    public void setArtikel(ArtikelDto artikel) {
        this.artikel = artikel;
    }

    public Date getTglKomentar() {
        return tglKomentar;
    }

    public void setTglKomentar(Date tglKomentar) {
        this.tglKomentar = tglKomentar;
    }

    public String getIsiKomentar() {
        return isiKomentar;
    }

    public void setIsiKomentar(String isiKomentar) {
        this.isiKomentar = isiKomentar;
    }
}
