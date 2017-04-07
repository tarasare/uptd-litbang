package com.sopiyan.uptd.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sopiyan on 06/02/2017.
 */
public class ArtikelDto {
    private String idArtikel;
    @NotBlank(message = "Judul artikel tidak boleh kosong")
    @Size(min = 5, max = 100, message = "Judul artikel harus minimal 5 - 100 karakter")
    private String judulArtikel;
    @NotBlank(message = "Isi artikel tidak boleh kosong")
    @Size(min = 10,message = "Isi artikel minimal 10 karakter")
    private String konten;
    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern="dd/MM/yyyy HH:MM")
    private Date tanggalPost;
    private String thumbnail;
    private UserDto user;
    private int visitor;
	@NotNull(message = "Anda Belum Memilih Kategori")
    private KategoriArtikelDto kategoriArtikel;

    public ArtikelDto() {
        thumbnail= "/assets/img/default.jpg";
        tanggalPost = new Date();
        visitor = 0;
    }

    public String getIdArtikel() {
        return idArtikel;
    }

    public void setIdArtikel(String idArtikel) {
        this.idArtikel = idArtikel;
    }

    public String getJudulArtikel() {
        return judulArtikel;
    }

    public void setJudulArtikel(String judulArtikel) {
        this.judulArtikel = judulArtikel;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public Date getTanggalPost() {
        return tanggalPost;
    }

    public void setTanggalPost(Date tanggalPost) {
        this.tanggalPost = tanggalPost;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public int getVisitor() {
        return visitor;
    }

    public void setVisitor(int visitor) {
        this.visitor = visitor;
    }

    public KategoriArtikelDto getKategoriArtikel() {
        return kategoriArtikel;
    }

    public void setKategoriArtikel(KategoriArtikelDto kategoriArtikel) {
        this.kategoriArtikel = kategoriArtikel;
    }
}
