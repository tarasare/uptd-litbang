package com.sopiyan.uptd.utils.validator;

import com.sopiyan.uptd.entities.dto.KategoriDto;
import com.sopiyan.uptd.entities.entity.Kategori;
import com.sopiyan.uptd.services.service.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Sopiyan on 20/02/2017.
 */
@Component
public class KategoriValidator implements Validator {
    @Autowired
    private KategoriService kategoriService;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(KategoriDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        KategoriDto kategoriDto = (KategoriDto) o;
        validasiNamaKategori(kategoriDto, errors);
    }
    private void validasiNamaKategori(KategoriDto kategoriDto, Errors errors){
        Kategori k = kategoriService.tampilkanBerdasarKanNamaKategori(kategoriDto.getNamaKategori());
        if(k != null){
            if(!k.getIdKategori().equals(kategoriDto.getIdKategori())){
                errors.reject("kategori","Nama kategori sudah tersedia");
            }
        }
    }
}
