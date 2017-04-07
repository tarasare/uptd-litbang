package com.sopiyan.uptd.controller.api;

import com.sopiyan.uptd.entities.dto.KategoriArtikelDto;
import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import com.sopiyan.uptd.services.service.KategoriArtikelService;
import com.sopiyan.uptd.utils.helper.KategoriArtikelHelper;
import com.sopiyan.uptd.utils.validator.KategoriArtikelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by sopiyan on 16/03/2017.
 */
@RestController
public class KategoriArtikelController {
    @Autowired
    private KategoriArtikelService kategoriArtikelService;
    @Autowired
    private KategoriArtikelValidator kategoriArtikelValidator;
    @InitBinder(value = "kategoriArtikelDto")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(kategoriArtikelValidator);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/dashboard/kategori/artikel", method = RequestMethod.POST)
    public ResponseEntity simpanKategoriArtikel(@Valid @RequestBody KategoriArtikelDto kategoriArtikelDto){
        try{
            KategoriArtikel k = new KategoriArtikel();
            k.setNamaKategoriArtikel(kategoriArtikelDto.getNamaKategoriArtikel());
            if(kategoriArtikelDto.getDeskripsiKategoriArtikel() != null){
                k.setDeskripsiKategoriArtikel(kategoriArtikelDto.getDeskripsiKategoriArtikel());
            }
            kategoriArtikelService.simpan(k);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/public/kategori/artikel", method = RequestMethod.GET)
    public ResponseEntity<Page<KategoriArtikelDto>> tampilkanSemuaKategoriArtikel(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        try{
            PageRequest pageRequest = new PageRequest(page >= 0?page:0,size > 0?size:20);
            Page<KategoriArtikel> pageKategoriArtikel = kategoriArtikelService.tampilkanSemua(pageRequest);
            return new ResponseEntity<Page<KategoriArtikelDto>>(KategoriArtikelHelper.convertFromPageKategoriArtikel(pageKategoriArtikel), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Page<KategoriArtikelDto>>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/dashboard/kategori/artikel", method = RequestMethod.PUT)
    public ResponseEntity perbaruiKategoriArtikel(@Valid @RequestBody KategoriArtikelDto kategoriArtikelDto){
        try{
            KategoriArtikel k = kategoriArtikelService.tampilkanBerdasarkanID(kategoriArtikelDto.getIdKategoriArtikel());
            k.getListArtikel();
            k.setNamaKategoriArtikel(kategoriArtikelDto.getNamaKategoriArtikel());
            if(kategoriArtikelDto.getDeskripsiKategoriArtikel() != null){
                k.setDeskripsiKategoriArtikel(kategoriArtikelDto.getDeskripsiKategoriArtikel());
            }
            kategoriArtikelService.perbarui(k);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/dashboard/kategori/artikel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity hapusKategoriArtikel(@PathVariable(value = "id")String id){
        try{
            KategoriArtikel k = kategoriArtikelService.tampilkanBerdasarkanID(id);
            k.getListArtikel();
            kategoriArtikelService.hapus(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/public/kategori/artikel/{id}", method = RequestMethod.GET)
    public ResponseEntity cariKategoriBerdasarkanID(@PathVariable(value = "id")String id){
        try{
            KategoriArtikel k = kategoriArtikelService.tampilkanBerdasarkanID(id);
            k.getListArtikel();
            return new ResponseEntity(KategoriArtikelHelper.convertFromKategoriArtikel(k), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
