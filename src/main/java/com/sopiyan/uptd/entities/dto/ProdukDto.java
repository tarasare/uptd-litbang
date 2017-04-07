package com.sopiyan.uptd.entities.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Sopiyan on 06/02/2017.
 */
public class ProdukDto {
    private String idProduk;
    private String kodeProduk;
    @NotBlank(message = "Nama Produk tidak boleh kosong")
    @Size(min = 3, max = 100, message = "Nama Produk harus 3 - 100 karakter")
    private String namaProduk;
    @NotNull(message = "Anda Belum Upload Gambar Produk")
    private String gambarProduk;
    @NotBlank(message = "Deskripsi produk tidak boleh kosong")
    @Size(min = 3, message = "Deskripsi produk harus minimal 3 karakter")
    private String deskripsiProduk;
    @NotNull(message = "Anda Belum Mengisi Harga")
    private BigDecimal hargaProduk;
    @NotNull(message = "Anda Belum Memilih Kategori")
    private KategoriDto kategori;
    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern="dd MMMM yyyy")
    private Date tglUpdate;
    private int dilihat;
    private UserDto user;
    private Set<KomentarDto> listKomentar;
    public ProdukDto() {
        dilihat = 0;
        tglUpdate = new Date();
		kategori = new KategoriDto();
        listKomentar = new HashSet<>();
    }

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getGambarProduk() {
        return gambarProduk;
    }

    public void setGambarProduk(String gambarProduk) {
        this.gambarProduk = gambarProduk;
    }

    public String getDeskripsiProduk() {
        return deskripsiProduk;
    }

    public void setDeskripsiProduk(String deskripsiProduk) {
        this.deskripsiProduk = deskripsiProduk;
    }

    public BigDecimal getHargaProduk() {
        return hargaProduk;
    }

    public void setHargaProduk(BigDecimal hargaProduk) {
        this.hargaProduk = hargaProduk;
    }

    public KategoriDto getKategori() {
        return kategori;
    }

    public void setKategori(KategoriDto kategori) {
        this.kategori = kategori;
    }

    public int getDilihat() {
        return dilihat;
    }

    public void setDilihat(int dilihat) {
        this.dilihat = dilihat;
    }

    public Date getTglUpdate() {
        return tglUpdate;
    }

    public void setTglUpdate(Date tglUpdate) {
        this.tglUpdate = tglUpdate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Set<KomentarDto> getListKomentar() {
        return listKomentar;
    }

    public void setListKomentar(Set<KomentarDto> listKomentar) {
        this.listKomentar = listKomentar;
    }
}
