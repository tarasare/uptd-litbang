package com.sopiyan.uptd.entities.entity;

import com.sopiyan.uptd.utils.enumeration.JenisPercakapan;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sopiyan on 05/02/2017.
 */
@Entity
@Table(name = "percakapan_tiket")
public class PercakapanTiket implements Serializable, Comparable<PercakapanTiket>{
    @Id
    @Column(name = "id_percakapan_tiket", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idPercakapan;
    @Basic(optional = false)
    @Column(name = "tanggal_percakapan", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglPercakapan;
    @Basic(optional = false)
    @Column(name = "isi_percakapan", nullable = false)
    private String isiPercakapan;
    @Column(name = "jenis_percakapan", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private JenisPercakapan jenisPercakapan;
    @Basic(optional = false)
    @Column(name = "checked", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean checked;
    @Basic(optional = false)
    @JoinColumn(name = "id_user_order", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;
    @Basic(optional = false)
    @JoinColumn(name = "id_tiket_percakapan", referencedColumnName = "id_tiket", nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Tiket tiket;

    public String getIdPercakapan() {
        return idPercakapan;
    }

    public void setIdPercakapan(String idPercakapan) {
        this.idPercakapan = idPercakapan;
    }

    public Date getTglPercakapan() {
        return tglPercakapan;
    }

    public void setTglPercakapan(Date tglPercakapan) {
        this.tglPercakapan = tglPercakapan;
    }

    public String getIsiPercakapan() {
        return isiPercakapan;
    }

    public void setIsiPercakapan(String isiPercakapan) {
        this.isiPercakapan = isiPercakapan;
    }

    public JenisPercakapan getJenisPercakapan() {
        return jenisPercakapan;
    }

    public void setJenisPercakapan(JenisPercakapan jenisPercakapan) {
        this.jenisPercakapan = jenisPercakapan;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tiket getTiket() {
        return tiket;
    }

    public void setTiket(Tiket tiket) {
        this.tiket = tiket;
    }

    @Override
    public int compareTo(PercakapanTiket percakapanTiket) {
        return 0;
    }
}
