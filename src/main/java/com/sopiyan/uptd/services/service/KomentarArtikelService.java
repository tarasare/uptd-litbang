package com.sopiyan.uptd.services.service;

import com.sopiyan.uptd.entities.entity.Artikel;
import com.sopiyan.uptd.entities.entity.KomentarArtikel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by sopiyan on 17/03/2017.
 */
public interface KomentarArtikelService {
    KomentarArtikel simpan(KomentarArtikel komentar);
    void hapus(String id);
    Page<KomentarArtikel> tampilkanSemuaKomentarBerdasarkanArtikel(Artikel artikel, Pageable page);
    Page<KomentarArtikel> komentarTerbaru(Pageable page);
    KomentarArtikel tampilkanBerdasarkanID(String id);
    Long hitungKomentarBerdasarkanArtikel(Artikel artikel);
}
