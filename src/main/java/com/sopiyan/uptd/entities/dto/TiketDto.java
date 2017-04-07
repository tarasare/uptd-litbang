package com.sopiyan.uptd.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.sopiyan.uptd.utils.enumeration.StatusTiket;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Sopiyan on 06/02/2017.
 */
public class TiketDto {
    private String idTiket;
    @NotBlank(message = "Judul tiket tidak boleh kosong")
    @Size(min = 5, max = 100, message = "Judul tiket harus minimal 5 - 100 karakter")
    private String judulTiket;
    private String kodeTiket;
    private StatusTiket statusTiket;
    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern="dd/MM/yyyy HH:MM")
    private Date tglTiket;
    private UserDto user;
    private Collection<PercakapanTiketDto> listPercakapanTiket ;

    public TiketDto() {
        listPercakapanTiket = new ArrayList<>();
    }

    public String getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(String idTiket) {
        this.idTiket = idTiket;
    }

    public String getJudulTiket() {
        return judulTiket;
    }

    public void setJudulTiket(String judulTiket) {
        this.judulTiket = judulTiket;
    }

    public String getKodeTiket() {
        return kodeTiket;
    }

    public void setKodeTiket(String kodeTiket) {
        this.kodeTiket = kodeTiket;
    }

    public StatusTiket getStatusTiket() {
        return statusTiket;
    }

    public void setStatusTiket(StatusTiket statusTiket) {
        this.statusTiket = statusTiket;
    }

    public Date getTglTiket() {
        return tglTiket;
    }

    public void setTglTiket(Date tglTiket) {
        this.tglTiket = tglTiket;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Collection<PercakapanTiketDto> getListPercakapanTiket() {
        return listPercakapanTiket;
    }

    public void setListPercakapanTiket(Collection<PercakapanTiketDto> listPercakapanTiket) {
        this.listPercakapanTiket = listPercakapanTiket;
    }
}
