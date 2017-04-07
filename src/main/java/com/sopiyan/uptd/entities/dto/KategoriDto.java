package com.sopiyan.uptd.entities.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sopiyan on 06/02/2017.
 */
public class KategoriDto {
    private String idKategori;
    @NotBlank(message = "Nama kategori tidak boleh kosong")
    @Size(min = 3, max = 50, message = "Nama kategori harus 3 - 50 karakter")
    private String namaKategori;
    private String keteraganKategori;
    private Set<ProdukDto> listProduk;

    public KategoriDto() {
        listProduk = new HashSet<ProdukDto>();
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getKeteraganKategori() {
        return keteraganKategori;
    }

    public void setKeteraganKategori(String keteraganKategori) {
        this.keteraganKategori = keteraganKategori;
    }

    public Set<ProdukDto> getListProduk() {
        return listProduk;
    }

    public void setListProduk(Set<ProdukDto> listProduk) {
        this.listProduk = listProduk;
    }
}
