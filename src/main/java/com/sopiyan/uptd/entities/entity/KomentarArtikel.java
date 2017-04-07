package com.sopiyan.uptd.entities.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sopiyan on 16/03/2017.
 */
@Entity
@Table(name = "komentar_artikel")
public class KomentarArtikel implements Serializable, Comparable<KomentarArtikel> {
    @Id
    @Column(name = "id_komentar_artikel", nullable = false, unique = true, length = 100)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idKomentar;
    @Basic(optional = false)
    @JoinColumn(name = "id_user_komentar", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;
    @Basic(optional = false)
    @JoinColumn(name = "id_artikel_komentar", referencedColumnName = "id_artikel", nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Artikel artikel;
    @Basic(optional = false)
    @Column(name = "tanggal_komentar", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglKomentar;
    @Basic(optional = false)
    @Column(name = "isi_komentar", nullable = false)
    private String isiKomentar;

    public String getIdKomentar() {
        return idKomentar;
    }

    public void setIdKomentar(String idKomentar) {
        this.idKomentar = idKomentar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
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

    @Override
    public int compareTo(KomentarArtikel komentar) {
        return this.idKomentar.compareTo(komentar.getIdKomentar());
    }
}
