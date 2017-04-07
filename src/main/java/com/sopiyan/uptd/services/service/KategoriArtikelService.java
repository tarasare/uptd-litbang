package com.sopiyan.uptd.services.service;

import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Sopiyan on 10/02/2017.
 */
public interface KategoriArtikelService {
    KategoriArtikel simpan(KategoriArtikel kategoriArtikel);
    KategoriArtikel perbarui(KategoriArtikel kategoriArtikel);
    void hapus(String id);
    Page<KategoriArtikel> tampilkanSemua(Pageable page);
    KategoriArtikel tampilkanBerdasarkanID(String id);
    KategoriArtikel tampilkanBerdasarkanNamaKategoriArtikel(String nama);
}
