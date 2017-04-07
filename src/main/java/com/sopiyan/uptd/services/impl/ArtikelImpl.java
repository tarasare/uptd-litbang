package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.Artikel;
import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.repositories.ArtikelRepository;
import com.sopiyan.uptd.services.service.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Sopiyan on 10/02/2017.
 */
@Service
public class ArtikelImpl implements ArtikelService {
    @Autowired
    private ArtikelRepository artikelRepository;
    @Override
    public Artikel simpan(Artikel artikel) {
        return artikelRepository.save(artikel);
    }

    @Override
    public Artikel perbarui(Artikel artikel) {
        return artikelRepository.save(artikel);
    }

    @Override
    public void hapus(String id) {
        artikelRepository.delete(id);
    }

    @Override
    public Artikel tampilkanBerdasarkanId(String idArtikel) {
        return artikelRepository.findOne(idArtikel);
    }

    @Override
    public Page<Artikel> tampilkanBerdasarkanJudul(String judul, Pageable page) {
        return artikelRepository.findByJudulArtikelContainingOrderByTanggalPostDesc(judul, page);
    }

    @Override
    public Page<Artikel> tampilkanBerdasarkanKategori(KategoriArtikel kategoriArtikel, Pageable page) {
        return artikelRepository.findByKategoriArtikelOrderByTanggalPostDesc(kategoriArtikel, page);
    }

    @Override
    public Page<Artikel> tampilkanSemua(Pageable page) {
        return artikelRepository.findAll(page);
    }

    @Override
    public Page<Artikel> tampilkanBerdasarkanUser(User user, Pageable page) {
        return artikelRepository.findByUserOrderByTanggalPostDesc(user,page);
    }

    @Override
    public Page<Artikel> tampilkanHotArtikel(Pageable page) {
        return artikelRepository.findTop10ByOrderByVisitorDesc(page);
    }

    @Override
    public Long hitungJumlahArtikel() {
        return artikelRepository.count();
    }
}
