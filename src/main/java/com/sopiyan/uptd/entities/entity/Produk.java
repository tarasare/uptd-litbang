package com.sopiyan.uptd.entities.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


/**
 * Created by Sopiyan on 03/02/2017.
 */
@Entity
@Table(name = "produk")
public class Produk implements Serializable, Comparable<Produk>{
    @Id
    @Column(name = "id_produk", nullable = false, unique = true, length = 100)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idProduk;
    @Basic(optional = false)
    @Column(name = "kode_produk", nullable = false, unique = true, length = 60)
    private String kodeProduk;
    @Basic(optional = false)
    @Column(name = "nama_produk", nullable = false, length = 100)
    private String namaProduk;
    @Basic(optional = false)
    @Column(name = "gambar_produk", nullable = false)
    private String gambarProduk;
    @Column(name = "deskripsi_produk", columnDefinition = "LONGTEXT")
    @Lob
    private String deskripsiProduk;
    @Basic(optional = false)
    @Column(name = "harga_produk", nullable = false)
    private BigDecimal hargaProduk;
    @Basic(optional = false)
    @JoinColumn(name = "id_kategori_produk", referencedColumnName = "id_kategori",nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Kategori kategori;
    @Basic(optional = false)
    @Column(name = "dilihat", nullable = false)
    private int dilihat;
    @Basic(optional = false)
    @Column(name = "tanggal_update", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglUpdate;
    @Basic(optional = false)
    @JoinColumn(name = "id_user_produk", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;
    @OneToMany(mappedBy = "produk", orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<Komentar> listKomentar = new HashSet<>();

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
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

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Komentar> getListKomentar() {
        return listKomentar;
    }

    public void setListKomentar(Set<Komentar> listKomentar) {
        this.listKomentar = listKomentar;
    }

    @Override
    public int compareTo(Produk o) {
        return this.idProduk.compareTo(o.getIdProduk());
    }
}
