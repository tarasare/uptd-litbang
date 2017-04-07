package com.sopiyan.uptd.entities.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sopiyan on 03/02/2017.
 */
@Entity
@Table(name = "kategori")
public class Kategori implements Serializable, Comparable<Kategori>{
    @Id
    @Column(name = "id_kategori", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private String idKategori;
    @Basic(optional = false)
    @Column(name = "nama_kategori", nullable = false, unique = true, length = 50)
    private String namaKategori;
    @Column(name = "keterangan_kategori")
    private String keteraganKategori;
    @OneToMany(mappedBy = "kategori", orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<Produk> listProduk = new HashSet<>();

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

    public String getKeteranganKategori() {
        return keteraganKategori;
    }

    public void setKeteranganKategori(String keteraganganKategori) {
        this.keteraganKategori = keteraganganKategori;
    }

    public Set<Produk> getListProduk() {
        return listProduk;
    }

    public void setListProduk(Set<Produk> listProduk) {
        this.listProduk = listProduk;
    }

    @Override
    public int compareTo(Kategori o) {
        return this.idKategori.compareTo(o.getIdKategori());
    }
}
