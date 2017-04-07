package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import com.sopiyan.uptd.services.repositories.KategoriArtikelRepository;
import com.sopiyan.uptd.services.service.KategoriArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Sopiyan on 10/02/2017.
 */
@Service
public class KategoriArtikelImpl implements KategoriArtikelService {
    @Autowired
    private KategoriArtikelRepository kategoriArtikelRepository;
    @Override
    public KategoriArtikel simpan(KategoriArtikel kategoriArtikel) {
        return kategoriArtikelRepository.save(kategoriArtikel);
    }

    @Override
    public KategoriArtikel perbarui(KategoriArtikel kategoriArtikel) {
        return kategoriArtikelRepository.save(kategoriArtikel);
    }

    @Override
    public void hapus(String id) {
        kategoriArtikelRepository.delete(id);
    }

    @Override
    public Page<KategoriArtikel> tampilkanSemua(Pageable page) {
        return kategoriArtikelRepository.findAll(page);
    }

    @Override
    public KategoriArtikel tampilkanBerdasarkanID(String id) {
        return kategoriArtikelRepository.findOne(id);
    }

    @Override
    public KategoriArtikel tampilkanBerdasarkanNamaKategoriArtikel(String nama) {
        return kategoriArtikelRepository.findOneByNamaKategoriArtikel(nama);
    }
}
