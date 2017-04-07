package com.sopiyan.uptd.controller.api;

import com.sopiyan.uptd.entities.dto.ArtikelDto;
import com.sopiyan.uptd.entities.entity.Artikel;
import com.sopiyan.uptd.entities.entity.Kategori;
import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.impl.CurrentUser;
import com.sopiyan.uptd.services.service.ArtikelService;
import com.sopiyan.uptd.services.service.KategoriArtikelService;
import com.sopiyan.uptd.services.service.UserService;
import com.sopiyan.uptd.utils.enumeration.Role;
import com.sopiyan.uptd.utils.helper.ArtikelHelper;
import com.sopiyan.uptd.utils.validator.ArtikelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Sopiyan on 17/02/2017.
 */
@RestController
public class ArtikelController {
    @Autowired
    private ArtikelService artikelService;
    @Autowired
    private KategoriArtikelService kategoriArtikelService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArtikelValidator artikelValidator;
    @InitBinder(value="artikelDto")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(artikelValidator);
    }
    /*
    #/public/artikel?user=iduser (jika ingin menampilkan artikel berdasarkan user)
    #/public/artikel?page=page&size=size (jika ingin mengatur jumlah dan page artikel yang ingin di tampilkan)
    #/public/artikel (Akan menampilkan artikel dengan page dan jumlah default yaitu page 0 dan size 20)
    # Saat ingin menampilkan berdasarkan user dengan id user tapi IDnya salah maka akan mengembalikan status code 204
 */
    @RequestMapping(value = "/public/artikel", method = RequestMethod.GET)
    public ResponseEntity<Page<ArtikelDto>> lihatArtikel(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
            @RequestParam(value = "user", required = false) String idUser,
            @RequestParam(name = "kategori", required = false) String idKategori
    ) throws Exception {
        try{
            Page<Artikel> pageArtikel;
            PageRequest pageRequest = new PageRequest(page, size);
            if(idUser != null){
                User user = userService.cariBerdasarkanID(idUser);
                user.getListArtikel();
                pageArtikel = artikelService.tampilkanBerdasarkanUser(user, pageRequest);
                return new ResponseEntity<Page<ArtikelDto>>(ArtikelHelper.convertFromPageArtikel(pageArtikel), HttpStatus.OK);
            }else {
                if(idKategori != null){
                    KategoriArtikel kategoriArtikel = kategoriArtikelService.tampilkanBerdasarkanID(idKategori);
                    kategoriArtikel.getListArtikel();
                    pageArtikel = artikelService.tampilkanBerdasarkanKategori(kategoriArtikel, pageRequest);
                    return new ResponseEntity<Page<ArtikelDto>>(ArtikelHelper.convertFromPageArtikel(pageArtikel), HttpStatus.OK);
                }else {
                    pageArtikel = artikelService.tampilkanSemua(pageRequest);
                    return new ResponseEntity<Page<ArtikelDto>>(ArtikelHelper.convertFromPageArtikel(pageArtikel), HttpStatus.OK);
                }
            }
        }catch (Exception ex){
            return new ResponseEntity<Page<ArtikelDto>>(HttpStatus.BAD_REQUEST);
        }
    }
    /*
    #/public/artikel/top (akan menampilkan 10 artikel teratas berdasarkan jumlah di lihat)
    */
    @RequestMapping(value = "/public/artikel/top", method = RequestMethod.GET)
    public ResponseEntity<Page<ArtikelDto>> lihat10ArtikelHot()throws Exception{
        Page<Artikel> pageArtikel = artikelService.tampilkanHotArtikel(new PageRequest(0,10));
        return new ResponseEntity<Page<ArtikelDto>>(ArtikelHelper.convertFromPageArtikel(pageArtikel), HttpStatus.OK);
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/artikel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity hapusArtikel(@PathVariable(value = "id") String idArtikel, Authentication authentication) {
        try{
            Artikel a = artikelService.tampilkanBerdasarkanId(idArtikel);
            a.getKategoriArtikel();
            User user = ((CurrentUser) authentication.getPrincipal()).getUser();
            user.getListArtikel();
            if(user.compareTo(a.getUser()) != -1 || user.getRole() == Role.ROLE_ADMIN){
                artikelService.hapus(idArtikel);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/public/artikel/{id}", method = RequestMethod.GET)
    public ResponseEntity<ArtikelDto> cariBerdasarkanID(@PathVariable(value = "id")String idArtikel){
        try{
            Artikel a = artikelService.tampilkanBerdasarkanId(idArtikel);
            a.getJudulArtikel();
            a.setVisitor(a.getVisitor() +1);
            artikelService.perbarui(a);
            return new ResponseEntity<ArtikelDto>(ArtikelHelper.convertFromArtikel(a), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ArtikelDto>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/artikel", method = RequestMethod.PUT)
    public ResponseEntity editArtikel(@Valid @RequestBody ArtikelDto artikelDto, Authentication authentication){
        try{
            KategoriArtikel k = kategoriArtikelService.tampilkanBerdasarkanID(artikelDto.getKategoriArtikel().getIdKategoriArtikel());
            k.getListArtikel();
            Artikel a = artikelService.tampilkanBerdasarkanId(artikelDto.getIdArtikel());
            a.getKategoriArtikel();
            a.setJudulArtikel(artikelDto.getJudulArtikel());
            a.setKategoriArtikel(k);
            a.setThumbnail(artikelDto.getThumbnail());
            a.setKonten(artikelDto.getKonten());
            a.setTanggalPost(new Date());
            User user = ((CurrentUser) authentication.getPrincipal()).getUser();
            if(user.getRole() == Role.ROLE_ADMIN || user.compareTo(a.getUser()) != -1){
                a = artikelService.perbarui(a);
                return new ResponseEntity(HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/artikel", method = RequestMethod.POST)
    public ResponseEntity<ArtikelDto> simpanArtikel(@Valid @RequestBody ArtikelDto artikelDto, Authentication authentication){
        try{
            KategoriArtikel k = kategoriArtikelService.tampilkanBerdasarkanID(artikelDto.getKategoriArtikel().getIdKategoriArtikel());
            k.getListArtikel();
            User user = ((CurrentUser) authentication.getPrincipal()).getUser();
            user.getListArtikel();
            Artikel a =new Artikel();
            a.setTanggalPost(new Date());
            a.setKonten(artikelDto.getKonten());
            a.setThumbnail(artikelDto.getThumbnail());
            a.setKategoriArtikel(k);
			a.setUser(user);
            a.setJudulArtikel(artikelDto.getJudulArtikel());
            a.setVisitor(0);
            a = artikelService.simpan(a);
            return new ResponseEntity<ArtikelDto>(ArtikelHelper.convertFromArtikel(a), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ArtikelDto>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/public/artikel/cari", method = RequestMethod.GET)
    public ResponseEntity<Page<ArtikelDto>> cariArtikel(
            @RequestParam(name = "judul") String judul,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size
    ){
        try{
            PageRequest pageRequest = new PageRequest(page, size);
            Page<Artikel> pageArtikel = artikelService.tampilkanBerdasarkanJudul(judul, pageRequest);
            return new ResponseEntity<Page<ArtikelDto>>(ArtikelHelper.convertFromPageArtikel(pageArtikel), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<Page<ArtikelDto>>(HttpStatus.BAD_REQUEST);
        }
    }
}
