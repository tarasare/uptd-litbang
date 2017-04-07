package com.sopiyan.uptd.entities.entity;

import com.sopiyan.uptd.utils.enumeration.Gender;
import com.sopiyan.uptd.utils.enumeration.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Sopiyan on 01/02/2017.
 */
@Entity
@Table(name = "user")
public class User implements Serializable, Comparable<User>{
    @Id
    @Column(name = "id_user", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idUser;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Basic(optional = false)
    @Column(name = "password", nullable = false)
    private String password;
    @Basic(optional = false)
    @Column(name = "nama_lengkap", nullable = false, length = 100)
    private String namaLengkap;
    @Basic(optional = false)
    @Column(name = "photo", nullable = false)
    private String photo;
    @Basic(optional = false)
    @Column(name = "no_telp", nullable = false)
    private String noTelp;
    @Basic(optional = false)
    @Column(name = "alamat", nullable = false)
    private String alamat;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "tgl_join")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglJoin;
    @Column(name = "gender", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Artikel> listArtikel = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<PercakapanTiket> listPercakapanTiket = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Tiket> listTiket = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Produk> listProduk = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private ResetPassword resetPassword;
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getTglJoin() {
        return tglJoin;
    }

    public void setTglJoin(Date tglJoin) {
        this.tglJoin = tglJoin;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Collection<Artikel> getListArtikel() {
        return listArtikel;
    }

    public ResetPassword getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(ResetPassword resetPassword) {
        this.resetPassword = resetPassword;
    }

    public void setListArtikel(Collection<Artikel> listArtikel) {
        this.listArtikel = listArtikel;
    }

    public Collection<PercakapanTiket> getListPercakapanTiket() {
        return listPercakapanTiket;
    }

    public void setListPercakapanTiket(Collection<PercakapanTiket> listPercakapanTiket) {
        this.listPercakapanTiket = listPercakapanTiket;
    }

    public Collection<Tiket> getListTiket() {
        return listTiket;
    }

    public void setListTiket(Collection<Tiket> listTiket) {
        this.listTiket = listTiket;
    }

    public Collection<Produk> getListProduk() {
        return listProduk;
    }

    public void setListProduk(Collection<Produk> listProduk) {
        this.listProduk = listProduk;
    }

    @Override
    public int compareTo(User user) {
        return this.getIdUser().compareTo(user.getIdUser());
    }
}
