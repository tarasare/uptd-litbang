package com.sopiyan.uptd.entities.entity;

import com.sopiyan.uptd.utils.enumeration.StatusTiket;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Sopiyan on 05/02/2017.
 */
@Entity
@Table(name = "tiket")
public class Tiket implements Serializable, Comparable<Tiket>{
    @Id
    @Column(name= "id_tiket", nullable= false, unique= true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idTiket;
    @Basic(optional = false)
    @Column(name = "judul_tiket", nullable = false, length = 100)
    private String judulTiket;
    @Basic(optional = false)
    @Column(name = "kode_tiket", nullable = false, unique = true)
    private String kodeTiket;
    @Basic(optional = false)
    @Column(name = "status_tiket", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusTiket statusTiket;
    @Basic(optional = false)
    @Column(name = "tanggal_open_tiket", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglTiket;
    @Basic(optional = false)
    @JoinColumn(name = "id_user_order", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;
    @OneToMany(mappedBy = "tiket",orphanRemoval = true,fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Collection<PercakapanTiket> listPercakapanTiket = new ArrayList<PercakapanTiket>();

    public String getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(String idTiket) {
        this.idTiket = idTiket;
    }

    public String getJudulTiket() {
        return judulTiket;
    }

    public void setJudulTiket(String judulTiket) {
        this.judulTiket = judulTiket;
    }

    public Collection<PercakapanTiket> getListPercakapanTiket() {
        return listPercakapanTiket;
    }

    public String getKodeTiket() {
        return kodeTiket;
    }

    public void setKodeTiket(String kodeTiket) {
        this.kodeTiket = kodeTiket;
    }

    public StatusTiket getStatusTiket() {
        return statusTiket;
    }

    public void setStatusTiket(StatusTiket statusTiket) {
        this.statusTiket = statusTiket;
    }

    public Date getTglTiket() {
        return tglTiket;
    }

    public void setTglTiket(Date tglTiket) {
        this.tglTiket = tglTiket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<PercakapanTiket> getListPercakapanTiketi() {
        return listPercakapanTiket;
    }

    public void setListPercakapanTiket(Collection<PercakapanTiket> listPercakapanTiket) {
        this.listPercakapanTiket = listPercakapanTiket;
    }

    @Override
    public int compareTo(Tiket tiket) {
        return 0;
    }
}
