package com.sopiyan.uptd.entities.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sopiyan on 06/02/2017.
 */
public class KategoriArtikelDto {
    private String idKategoriArtikel;
    @NotBlank(message = "Nama kategori tidak boleh kosong")
    @Size(min = 3, max = 50, message = "Nama kategori harus 3 - 50 karakter")
    private String namaKategoriArtikel;
    private String deskripsiKategoriArtikel;
    private Set<ArtikelDto> listArtikel;
    public KategoriArtikelDto() {
        listArtikel = new HashSet<>();
    }

    public String getIdKategoriArtikel() {
        return idKategoriArtikel;
    }

    public void setIdKategoriArtikel(String idKategoriArtikel) {
        this.idKategoriArtikel = idKategoriArtikel;
    }

    public String getNamaKategoriArtikel() {
        return namaKategoriArtikel;
    }

    public void setNamaKategoriArtikel(String namaKategoriArtikel) {
        this.namaKategoriArtikel = namaKategoriArtikel;
    }

    public String getDeskripsiKategoriArtikel() {
        return deskripsiKategoriArtikel;
    }

    public void setDeskripsiKategoriArtikel(String deskripsiKategoriArtikel) {
        this.deskripsiKategoriArtikel = deskripsiKategoriArtikel;
    }

    public Set<ArtikelDto> getListArtikel() {
        return listArtikel;
    }

    public void setListArtikel(Set<ArtikelDto> listArtikel) {
        this.listArtikel = listArtikel;
    }
}
