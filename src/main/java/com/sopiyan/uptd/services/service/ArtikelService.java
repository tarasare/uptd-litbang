package com.sopiyan.uptd.services.service;

import com.sopiyan.uptd.entities.entity.Artikel;
import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import com.sopiyan.uptd.entities.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Sopiyan on 10/02/2017.
 */
public interface ArtikelService {
    Artikel simpan(Artikel artikel);
    Artikel perbarui(Artikel artikel);
    void hapus(String id);
    Artikel tampilkanBerdasarkanId(String idArtikel);
    Page<Artikel> tampilkanBerdasarkanJudul(String judul, Pageable page);
    Page<Artikel> tampilkanBerdasarkanKategori(KategoriArtikel kategoriArtikel, Pageable page);
    Page<Artikel> tampilkanSemua(Pageable page);
    Page<Artikel> tampilkanBerdasarkanUser(User user, Pageable page);
    Page<Artikel> tampilkanHotArtikel(Pageable page);
    Long hitungJumlahArtikel();
}
