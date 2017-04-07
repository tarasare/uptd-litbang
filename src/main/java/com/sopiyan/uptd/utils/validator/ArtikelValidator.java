package com.sopiyan.uptd.utils.validator;

import com.sopiyan.uptd.entities.dto.ArtikelDto;
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
public class ArtikelValidator implements Validator {
    @Autowired
    private KategoriArtikelService kategoriArtikelService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ArtikelDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ArtikelDto artikelDto = (ArtikelDto) o;
        validasiKategoriArtikel(artikelDto, errors);
    }
    private void validasiKategoriArtikel(ArtikelDto artikelDto, Errors errors){
        try{
			KategoriArtikel k = kategoriArtikelService.tampilkanBerdasarkanID(artikelDto.getKategoriArtikel().getIdKategoriArtikel());
        if(k == null){
            errors.reject("artikel.kategori_artikel", "Kategori tidak ditemukan");
        }
		}catch(Exception e){
			errors.reject("artikel.kategori_artikel", "Anda belum memilih kategori");
		}
    }
}
