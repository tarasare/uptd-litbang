package com.sopiyan.uptd.entities.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sopiyan on 08/02/2017.
 */
@Entity
@Table(name = "reset_password")
public class ResetPassword implements Serializable, Comparable<ResetPassword>{
    @Id
    @Basic(optional = false)
    @Column(name = "kode_order", nullable = false, unique = true)
    private String kodeReset;
    @Basic(optional = false)
    @Column(name = "tanggal_reset", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglReset;
    @JoinColumn(name = "id_user_reset_password", referencedColumnName = "id_user")
    @OneToOne()
    private User user;


    public String getKodeReset() {
        return kodeReset;
    }

    public void setKodeReset(String kodeReset) {
        this.kodeReset = kodeReset;
    }

    public Date getTglReset() {
        return tglReset;
    }

    public void setTglReset(Date tglReset) {
        this.tglReset = tglReset;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compareTo(ResetPassword resetPassword) {
        return 0;
    }
}
