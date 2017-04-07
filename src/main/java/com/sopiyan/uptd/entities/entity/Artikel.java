package com.sopiyan.uptd.entities.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sopiyan on 03/02/2017.
 */
@Entity
@Table(name = "artikel")
public class Artikel implements Serializable, Comparable<Artikel>{
    @Id
    @Column(name = "id_artikel", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idArtikel;
    @Basic(optional = false)
    @Column(name = "judul_artikel", nullable = false, length = 100)
    private String judulArtikel;
    @Basic(optional = false)
    @Column(name = "konten_artikel", columnDefinition = "LONGTEXT", nullable = false)
    @Lob
    private String konten;
    @Basic(optional = false)
    @Column(name = "tanggal_post", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalPost;
    @Basic(optional = false)
    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;
    @Basic(optional = false)
    @JoinColumn(name = "id_user_artikel", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;
    @Column(name = "visitor")
    private int visitor;
    @Basic(optional = false)
    @JoinColumn(name = "id_kategori_artikel", referencedColumnName = "id_kategori_artikel",nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private KategoriArtikel kategoriArtikel;
    @OneToMany(mappedBy = "artikel", orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<KomentarArtikel> listKomentarArtikel = new HashSet<>();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getVisitor() {
        return visitor;
    }

    public void setVisitor(int visitor) {
        this.visitor = visitor;
    }

    public KategoriArtikel getKategoriArtikel() {
        return kategoriArtikel;
    }

    public void setKategoriArtikel(KategoriArtikel kategoriArtikel) {
        this.kategoriArtikel = kategoriArtikel;
    }

    public Set<KomentarArtikel> getListKomentarArtikel() {
        return listKomentarArtikel;
    }

    public void setListKomentarArtikel(Set<KomentarArtikel> listKomentarArtikel) {
        this.listKomentarArtikel = listKomentarArtikel;
    }

    @Override
    public int compareTo(Artikel artikel) {
        return this.idArtikel.compareTo(artikel.getIdArtikel());
    }
}
