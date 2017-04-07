package com.sopiyan.uptd.controller.api;

import com.sopiyan.uptd.entities.dto.KomentarArtikelDto;
import com.sopiyan.uptd.entities.entity.Artikel;
import com.sopiyan.uptd.entities.entity.KomentarArtikel;
import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.impl.CurrentUser;
import com.sopiyan.uptd.services.service.ArtikelService;
import com.sopiyan.uptd.services.service.KomentarArtikelService;
import com.sopiyan.uptd.services.service.KomentarService;
import com.sopiyan.uptd.utils.helper.KomentarArtikelHelper;
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
 * Created by sopiyan on 16/03/2017.
 */
@RestController
public class KomentarArtikelController {
    @Autowired
    private KomentarArtikelService komentarArtikelService;
    @Autowired
    private ArtikelService artikelService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/komentar/artikel", method = RequestMethod.POST)
    public ResponseEntity<KomentarArtikelDto> simpanKomentarArtikel(@Valid @RequestBody KomentarArtikelDto komentar, Authentication authentication) {
        try {
            KomentarArtikel k = new KomentarArtikel();
            k.setIsiKomentar(komentar.getIsiKomentar());
            k.setTglKomentar(new Date());
            User user = ((CurrentUser) authentication.getPrincipal()).getUser();
            k.setUser(user);
            Artikel a = artikelService.tampilkanBerdasarkanId(komentar.getArtikel().getIdArtikel());
            a.getListKomentarArtikel();
            k.setArtikel(a);
            k = komentarArtikelService.simpan(k);
            return new ResponseEntity<KomentarArtikelDto>(KomentarArtikelHelper.convertFromKometarArtikel(k), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<KomentarArtikelDto>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/komentar/artikel/{id}", method = RequestMethod.GET)
    public ResponseEntity<Page<KomentarArtikelDto>> tampilkanBerdasarkanArtikel(
            @PathVariable(value = "id")String idArtikel,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        try{
            PageRequest pageRequest = new PageRequest(page >= 0?page:0, size >=0 ? size:20);
            Artikel a = artikelService.tampilkanBerdasarkanId(idArtikel);
            a.getListKomentarArtikel();
            Page<KomentarArtikel> pageKomentar = komentarArtikelService.tampilkanSemuaKomentarBerdasarkanArtikel(a, pageRequest);
            return new ResponseEntity<Page<KomentarArtikelDto>>(KomentarArtikelHelper.convertFromPageKomentarArtikel(pageKomentar), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Page<KomentarArtikelDto>>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/dashboard/komentar/artikel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity hapusKomentarArtikel(@PathVariable(value = "id")String id){
        try{
            KomentarArtikel k = komentarArtikelService.tampilkanBerdasarkanID(id);
            k.getArtikel();
            komentarArtikelService.hapus(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/komentar/artikel/top", method = RequestMethod.GET)
    public ResponseEntity<Page<KomentarArtikelDto>> tampilkan10TerbaruKomentarArtikel(){
        try {
            Page<KomentarArtikel> pageKomentar = komentarArtikelService.komentarTerbaru(new PageRequest(0, 10));
            return new ResponseEntity<Page<KomentarArtikelDto>>(KomentarArtikelHelper.convertFromPageKomentarArtikel(pageKomentar), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
