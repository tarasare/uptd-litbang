package com.sopiyan.uptd.entities.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sopiyan on 05/02/2017.
 */
@Entity
@Table(name = "kategori_artikel")
public class KategoriArtikel implements Serializable, Comparable<KategoriArtikel>{
    @Id
    @Column(name = "id_kategori_artikel", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idKategoriArtikel;
    @Basic
    @Column(name = "nama_kategori_artikel",nullable = false,unique = true, length = 50)
    private String namaKategoriArtikel;
    @Column(name = "deskripsi_kategori_artikel")
    private String deskripsiKategoriArtikel;
    @OneToMany(mappedBy = "kategoriArtikel",orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Artikel> listArtikel = new HashSet<>();

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

    public Set<Artikel> getListArtikel() {
        return listArtikel;
    }

    public void setListArtikel(Set<Artikel> listArtikel) {
        this.listArtikel = listArtikel;
    }

    @Override
    public int compareTo(KategoriArtikel kategoriArtikel) {
        return this.idKategoriArtikel.compareTo(kategoriArtikel.getIdKategoriArtikel());
    }

}
