package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.Artikel;
import com.sopiyan.uptd.entities.entity.KomentarArtikel;
import com.sopiyan.uptd.services.repositories.KomentarArtikelRepository;
import com.sopiyan.uptd.services.service.KomentarArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by sopiyan on 17/03/2017.
 */
@Service
public class KomentarArtikelImpl  implements KomentarArtikelService{
    @Autowired
    private KomentarArtikelRepository komentarArtikelRepository;

    @Override
    public KomentarArtikel simpan(KomentarArtikel komentar) {
        return komentarArtikelRepository.save(komentar);
    }

    @Override
    public void hapus(String id) {
        KomentarArtikel k = komentarArtikelRepository.findOne(id);
        if(k != null){
            komentarArtikelRepository.delete(id);
        }
    }

    @Override
    public Page<KomentarArtikel> tampilkanSemuaKomentarBerdasarkanArtikel(Artikel artikel, Pageable page) {
        return komentarArtikelRepository.findByArtikelOrderByTglKomentarDesc(artikel, page);
    }

    @Override
    public Page<KomentarArtikel> komentarTerbaru(Pageable page) {
        return komentarArtikelRepository.findTop10ByOrderByTglKomentarDesc(page);
    }

    @Override
    public KomentarArtikel tampilkanBerdasarkanID(String id) {
        return komentarArtikelRepository.findOne(id);
    }

    @Override
    public Long hitungKomentarBerdasarkanArtikel(Artikel artikel) {
        return komentarArtikelRepository.countByArtikel(artikel);
    }
}
