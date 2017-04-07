package com.sopiyan.uptd.controller.api;

import com.sopiyan.uptd.entities.dto.KategoriDto;
import com.sopiyan.uptd.entities.entity.Kategori;
import com.sopiyan.uptd.entities.entity.Produk;
import com.sopiyan.uptd.services.service.KategoriService;
import com.sopiyan.uptd.utils.helper.KategoriHelper;
import com.sopiyan.uptd.utils.validator.KategoriValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Sopiyan on 20/02/2017.
 */
@RestController
public class KategoriController {
    @Autowired
    private KategoriService kategoriService;
    @Autowired
    private KategoriValidator kategoriValidator;
    @InitBinder("kategoriDto")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(kategoriValidator);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/dashboard/kategori", method = RequestMethod.POST)
    public ResponseEntity simpanKategori(@Valid @RequestBody KategoriDto kategoriDto){
        try{
            Kategori k = new Kategori();
            k.setNamaKategori(kategoriDto.getNamaKategori());
            if(kategoriDto.getKeteraganKategori() != null){
                k.setKeteranganKategori(kategoriDto.getKeteraganKategori());
            }
            kategoriService.simpan(k);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/public/kategori", method = RequestMethod.GET)
    public ResponseEntity<Page<KategoriDto>> tampilkanSemuaKategori(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        try{
            if(page < 0 || size < 0){
                page = 0;
                size = 20;
            }
            Page<Kategori> pageKategori = kategoriService.tampilkanSemua(new PageRequest(page, size));
            return new ResponseEntity<Page<KategoriDto>>(KategoriHelper.convertFromPageKategori(pageKategori), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Page<KategoriDto>>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/dashboard/kategori", method = RequestMethod.PUT)
    public ResponseEntity perbaruiKategori(@Valid @RequestBody KategoriDto kategoriDto){
        try{
            Kategori k = kategoriService.tampilkanBerdasarkanID(kategoriDto.getIdKategori());
            k.getListProduk();
            k.setNamaKategori(kategoriDto.getNamaKategori());
            k.setKeteranganKategori(kategoriDto.getKeteraganKategori());
            k = kategoriService.perbarui(k);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/dashboard/kategori/{id}", method = RequestMethod.DELETE)
    public ResponseEntity hapusKategori(@PathVariable(value = "id")String id){
        try{
            Kategori k = kategoriService.tampilkanBerdasarkanID(id);
            k.getListProduk();
            kategoriService.hapus(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/public/kategori/{id}", method = RequestMethod.GET)
    public ResponseEntity cariKategoriBerdasarkanID(@PathVariable(value = "id")String id){
        try{
            Kategori k = kategoriService.tampilkanBerdasarkanID(id);
            k.getListProduk();
            return new ResponseEntity(KategoriHelper.convertFromKategori(k), HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
