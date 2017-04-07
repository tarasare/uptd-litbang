package com.sopiyan.uptd.utils.validator;

import com.sopiyan.uptd.entities.dto.ProdukDto;
import com.sopiyan.uptd.entities.entity.Kategori;
import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.service.KategoriService;
import com.sopiyan.uptd.services.service.UserService;
import com.sopiyan.uptd.utils.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

/**
 * Created by Sopiyan on 19/02/2017.
 */
@Component
public class ProdukValidator implements Validator{

    @Autowired
    private UserService userService;
    @Autowired
    private KategoriService kategoriService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ProdukDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProdukDto produkDto = (ProdukDto) o;
        // validasiUser(produkDto, errors);
        validasiHarga(produkDto, errors);
        validasiKategori(produkDto, errors);
    }

 /*    private void validasiUser(ProdukDto produkDto, Errors errors){
        try{
            if(produkDto.getUser() != null){
                User user = userService.cariBerdasarkanID(produkDto.getUser().getIdUser());
                if(user != null){
                    if(user.getRole() != Role.ROLE_PENGRAJIN || user.getRole() != Role.ROLE_ADMIN){
                        errors.reject("user","Anda tidak punya akses");
                    }
                }else{
                    errors.reject("user","User tidak boleh kosong");
                }
            }else {
                errors.reject("user","User tidak boleh kosong");
            }
        }catch (Exception x){
            errors.reject("user", "user tidak boleh kosong");
        }

    } */

    private void validasiHarga(ProdukDto produkDto, Errors errors) {
        try {
            BigDecimal harga = new BigDecimal(produkDto.getHargaProduk().toBigInteger());
            double d  = harga.doubleValue();
            if(d <= 0 ){
                errors.reject("harga.min", "Harga Tidak Boleh Nol");
            }
        } catch (Exception e) {
            errors.reject("harga", "Masukan Angka pada Harga");
        }
    }

    private void validasiKategori(ProdukDto produkDto, Errors errors) {
        try {
            Kategori kategori = kategoriService.tampilkanBerdasarkanID(produkDto.getKategori().getIdKategori());
            kategori.getListProduk();
        } catch (NullPointerException ex) {
            errors.reject("kategori.not_exist", "Kategori Tidak Di temukan");
        } catch (Exception e) {
            errors.reject("kategori.not_exist", "Kategori Tidak Di temukan");
        }
    }

}
