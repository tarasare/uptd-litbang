package com.sopiyan.uptd.controller.api;

import com.sopiyan.uptd.entities.dto.KomentarDto;
import com.sopiyan.uptd.entities.entity.Komentar;
import com.sopiyan.uptd.entities.entity.Produk;
import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.impl.CurrentUser;
import com.sopiyan.uptd.services.service.KomentarService;
import com.sopiyan.uptd.services.service.ProdukService;
import com.sopiyan.uptd.utils.helper.KomentarHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by sopiyan on 07/03/2017.
 */
@RestController
public class KomentarController {
    @Autowired
    private KomentarService komentarService;
    @Autowired
    private ProdukService produkService;
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/komentar", method = RequestMethod.POST)
    public ResponseEntity<KomentarDto> simpanKomentar(@Valid @RequestBody KomentarDto komentar, Authentication authentication){
        try{
            Komentar k = new Komentar();
            k.setIsiKomentar(komentar.getIsiKomentar());
            k.setTglKomentar(new Date());
            try{
                User user = ((CurrentUser) authentication.getPrincipal()).getUser();
                k.setUser(user);
                Produk p = produkService.tampilkanBerdasarkanID(komentar.getProduk().getIdProduk());
                p.getListKomentar();
                k.setProduk(p);
            }catch (Exception ex){
                return new ResponseEntity<KomentarDto>(HttpStatus.BAD_REQUEST);
            }
            k = komentarService.simpan(k);
            KomentarDto komentarDto = KomentarHelper.converFromKomentar(k);
            return new ResponseEntity<KomentarDto>(komentarDto, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<KomentarDto>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/komentar/produk/{id}", method = RequestMethod.GET)
    public ResponseEntity<Page<KomentarDto>> tampilkanBerdasarkanProduk(
            @PathVariable(value = "id")String idProduk,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        try{
            PageRequest pageRequest = new PageRequest(page, size);
            Produk p = produkService.tampilkanBerdasarkanID(idProduk);
            p.getListKomentar();
            Page<Komentar> pageKomentar = komentarService.tampilkanSemuaKomentarBerdasarkanProduk(p, pageRequest);
            return new ResponseEntity<Page<KomentarDto>>(KomentarHelper.convertFromPageKomentar(pageKomentar), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Page<KomentarDto>>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/dashboard/komentar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity hapusKomentar(@PathVariable(value = "id")String id){
        try{
            Komentar k = komentarService.tampilkanBerdasarkanID(id);
            k.getProduk();
            komentarService.hapus(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/komentar/top", method = RequestMethod.GET)
    public ResponseEntity<Page<KomentarDto>> tampilkan10Terbaru(){
        try {
            Page<Komentar> pageKomentar = komentarService.komentarTerbaru(new PageRequest(0,10));
            return new ResponseEntity<Page<KomentarDto>>(KomentarHelper.convertFromPageKomentar(pageKomentar), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
