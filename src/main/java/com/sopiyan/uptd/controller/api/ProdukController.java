package com.sopiyan.uptd.controller.api;

import com.sopiyan.uptd.entities.dto.ProdukDto;
import com.sopiyan.uptd.entities.entity.Kategori;
import com.sopiyan.uptd.entities.entity.Produk;
import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.impl.CurrentUser;
import com.sopiyan.uptd.services.service.KategoriService;
import com.sopiyan.uptd.services.service.ProdukService;
import com.sopiyan.uptd.services.service.UserService;
import com.sopiyan.uptd.utils.enumeration.Role;
import com.sopiyan.uptd.utils.helper.ProdukHelper;
import com.sopiyan.uptd.utils.validator.ProdukValidator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sopiyan on 17/02/2017.
 */
@RestController
public class ProdukController {
    @Autowired
    private ProdukService produkService;
    @Autowired
    private UserService userService;
    @Autowired
    private KategoriService kategoriService;
    @Autowired
    private ProdukValidator produkValidator;

    @InitBinder(value = "produkDto")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(produkValidator);
    }

    /*
    #/public/produk?user=iduser (jika ingin menampilkan produk berdasarkan user)
    #/public/produk?page=page&size=size (jika ingin mengatur jumlah dan page produk yang ingin di tampilkan)
    #/public/produk (Akan menampilkan produk dengan page dan jumlah default yaitu page 0 dan size 20)
    # Saat ingin menampilkan berdasarkan user dengan id user tapi IDnya salah maka akan mengembalikan status code 204
     */
    @RequestMapping(value = "/public/produk", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdukDto>> lihatProduk(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
            @RequestParam(name = "user", required = false) String idUser,
            @RequestParam(name = "kategori", required = false) String idKategori
    ) throws Exception {
        try {
            Page<Produk> pageProduk;
            PageRequest pageRequest = new PageRequest(page, size);
            if (idUser != null) {
                User user = userService.cariBerdasarkanID(idUser);
                if (user != null) {
                    pageProduk = produkService.tampilkanBerdasarkanUser(user, pageRequest);
                    return new ResponseEntity<Page<ProdukDto>>(ProdukHelper.convertFromPageProduk(pageProduk), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Page<ProdukDto>>(HttpStatus.NO_CONTENT);
                }

            } else {
                if (idKategori != null) {
                    Kategori k = kategoriService.tampilkanBerdasarkanID(idKategori);
                    k.getListProduk();
                    pageProduk = produkService.tampilkanBerdasarkanKategori(k,pageRequest);
                    return new ResponseEntity<Page<ProdukDto>>(ProdukHelper.convertFromPageProduk(pageProduk), HttpStatus.OK);
                } else {
                    pageProduk = produkService.tampilkanSemua(pageRequest);
                    return new ResponseEntity<Page<ProdukDto>>(ProdukHelper.convertFromPageProduk(pageProduk), HttpStatus.OK);
                }
            }
        }catch (Exception ex){
         return new ResponseEntity<Page<ProdukDto>>(HttpStatus.BAD_REQUEST);
        }
    }

    /*
    #/public/produk/top (akan menampilkan 10 produk teratas berdasarkan jumlah di lihat)
     */
    @RequestMapping(value = "/public/produk/top", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdukDto>> lihat10ProdukHot() throws Exception {
        Page<Produk> pageProduk = produkService.tampilkanHotProduk(new PageRequest(0, 10));
        return new ResponseEntity<Page<ProdukDto>>(ProdukHelper.convertFromPageProduk(pageProduk), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PENGRAJIN')")
    @RequestMapping(value = "/secure2/produk/{id}", method = RequestMethod.DELETE)
    public ResponseEntity hapusProduk(@PathVariable(value = "id") String id, Authentication authentication) throws Exception {
        Produk p = produkService.tampilkanBerdasarkanID(id);
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        if (user.compareTo(p.getUser()) != -1) {
            produkService.hapus(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            if (user.getRole() == Role.ROLE_ADMIN) {
                produkService.hapus(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/public/produk/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProdukDto> tampilkanProdukBerdasarkanID(@PathVariable(value = "id") String idProduk) throws Exception {
        Produk produk = produkService.tampilkanBerdasarkanID(idProduk);
        if (produk != null) {
            ProdukDto produkDto = ProdukHelper.convertFromProduk(produk);
            produk.setDilihat(produk.getDilihat() + 1);
            produkService.perbarui(produk);
            return new ResponseEntity<ProdukDto>(produkDto, HttpStatus.OK);
        }
        return new ResponseEntity<ProdukDto>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PENGRAJIN')")
    @RequestMapping(value = "/secure2/produk", method = RequestMethod.PUT)
    public ResponseEntity perbaruiProduk(@Valid @RequestBody ProdukDto produkDto, Authentication authentication) {
        Kategori kategori = kategoriService.tampilkanBerdasarkanID(produkDto.getKategori().getIdKategori());
        Produk produk = produkService.tampilkanBerdasarkanID(produkDto.getIdProduk());
        produk.setNamaProduk(produkDto.getNamaProduk());
        produk.setGambarProduk(produkDto.getGambarProduk());
        produk.setDeskripsiProduk(produkDto.getDeskripsiProduk());
        produk.setKategori(kategori!=null?kategori:produk.getKategori());
        produk.setKodeProduk(produkDto.getKodeProduk());
        produk.setHargaProduk(produkDto.getHargaProduk());
        produk.setTglUpdate(new Date());
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        if (user.compareTo(produk.getUser()) != -1 || user.getRole() == Role.ROLE_ADMIN) {
            produkService.perbarui(produk);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','PENGRAJIN')")
    @RequestMapping(value = "/secure2/produk", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProdukDto> simpanProduk(@Valid @RequestBody ProdukDto produkDto, Authentication authentication) throws Exception {
        Kategori kategori = kategoriService.tampilkanBerdasarkanID(produkDto.getKategori().getIdKategori());
        Produk produk = new Produk();
        User user = ((CurrentUser) authentication.getPrincipal()).getUser();
        produk.setNamaProduk(produkDto.getNamaProduk());
        produk.setGambarProduk(produkDto.getGambarProduk());
        produk.setDeskripsiProduk(produkDto.getDeskripsiProduk());
        produk.setKategori(kategori);
        produk.setTglUpdate(produkDto.getTglUpdate());
        produk.setDilihat(0);
        produk.setKodeProduk(createKodeProduk());
        produk.setHargaProduk(produkDto.getHargaProduk());
        produk.setUser(user);
        if (user.getRole() == Role.ROLE_DEFAULT) {
            return new ResponseEntity<ProdukDto>(HttpStatus.BAD_REQUEST);
        } else {
            produk = produkService.simpan(produk);
            return new ResponseEntity<ProdukDto>(ProdukHelper.convertFromProduk(produk), HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/public/produk/cari", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdukDto>> cariBerdasarkanNama(
            @RequestParam(name = "nama") String namaProduk,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size
    ) {
        try {
            PageRequest pageRequest = new PageRequest(page, size);
            Page<Produk> pageProduk = produkService.tampilkanBerdasarkanNamaProduk(namaProduk, pageRequest);
            return new ResponseEntity<Page<ProdukDto>>(ProdukHelper.convertFromPageProduk(pageProduk), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Page<ProdukDto>>(HttpStatus.BAD_REQUEST);
        }
    }

    private String createKodeProduk() {
        DateTime dateTime = new DateTime();
        int tgl = dateTime.getDayOfMonth();
        int bln = dateTime.getMonthOfYear();
        int thn = dateTime.getYear();
        String tanggal = (tgl < 10) ? "0" + tgl : tgl + "";
        String bulan = (bln < 10) ? "0" + bln : bln + "";
        String tahun = new String("" + thn);
        String kode = tanggal + bulan + tahun.substring(3);
        Long jumlah = produkService.hitungJumlahProdukBerdasrkanKode(kode) + 1;
        return jumlah >= 10 ? kode + jumlah : kode + "0" + jumlah;
    }
}
