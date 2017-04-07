package com.sopiyan.uptd.utils.validator;

import com.sopiyan.uptd.entities.dto.KategoriArtikelDto;
import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import com.sopiyan.uptd.services.service.KategoriArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sopiyan on 16/03/2017.
 */
@Component
public class KategoriArtikelValidator implements Validator {
    @Autowired
    private KategoriArtikelService kategoriArtikelService;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(KategoriArtikelDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        KategoriArtikelDto kategoriArtikelDto = (KategoriArtikelDto) o;
        validasiNamaKategoriArtikel(kategoriArtikelDto, errors);
    }
    private void validasiNamaKategoriArtikel(KategoriArtikelDto kategoriArtikelDto, Errors errors){
        KategoriArtikel k = kategoriArtikelService.tampilkanBerdasarkanNamaKategoriArtikel(kategoriArtikelDto.getNamaKategoriArtikel());
        if(k != null){
            errors.reject("kategori_artikel",String.format("Nama Kategori %s sudah tersedia", kategoriArtikelDto.getNamaKategoriArtikel()));
        }
    }
}
