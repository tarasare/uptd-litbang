package com.sopiyan.uptd.entities.dto;

import com.sopiyan.uptd.utils.enumeration.Gender;
import com.sopiyan.uptd.utils.enumeration.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Sopiyan on 06/02/2017.
 */
public class UserDto {
    private String idUser;
    @Email(message = "Isi email dengan format yang benar ex:example@mail.com")
    @NotBlank(message = "Email Tidak boleh kosong")
    private String email;
    @NotBlank(message = "Password Tidak Boleh kosong")
    @Size(min = 6, max = 16, message = "Password harus minimal 6 - 16 karakter")
    private String password;
    @NotBlank(message = "Ulangi password")
    private String repeatPassword;
    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(min = 3, max = 100, message = "Password harus minimal 3 - 100 karakter")
    private String namaLengkap;
    private String photo;
    @NotBlank(message = "noTelp tidak boleh kosong")
    @Size(min = 10, max = 16, message = "noTelp minmal 10 dan maksimal 16")
    private String noTelp;
    @NotBlank(message = "Alamat tidak bolehg kosong")
    @Size(min = 3, max = 250, message = "Alamat harus minimal 3 - 100 karakter")
    private String alamat;
    private Role role;
    private Date tglJoin;
    @NotNull(message = "Pilih Jenis Kelamin")
    private Gender gender;
    private Collection<ProdukDto> listProduk;
    private Collection<ArtikelDto> listArtikel;
    private Collection<TiketDto> listTiket;
    private ResetPasswordDto resetPassword;
    private Collection<PercakapanTiketDto> listPercakapanTiket;
    public UserDto() {
        listArtikel = new ArrayList<>();
        listTiket = new ArrayList<>();
        listProduk= new ArrayList<>();
        listPercakapanTiket = new ArrayList<>();
        resetPassword = new ResetPasswordDto();
    }

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
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

    public Collection<ArtikelDto> getListArtikel() {
        return listArtikel;
    }

    public void setListArtikel(Collection<ArtikelDto> listArtikel) {
        this.listArtikel = listArtikel;
    }

    public Collection<TiketDto> getListTiket() {
        return listTiket;
    }

    public void setListTiket(Collection<TiketDto> listTiket) {
        this.listTiket = listTiket;
    }

    public ResetPasswordDto getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(ResetPasswordDto resetPassword) {
        this.resetPassword = resetPassword;
    }

    public Collection<PercakapanTiketDto> getListPercakapanTiket() {
        return listPercakapanTiket;
    }

    public void setListPercakapanTiket(Collection<PercakapanTiketDto> listPercakapanTiket) {
        this.listPercakapanTiket = listPercakapanTiket;
    }
}
