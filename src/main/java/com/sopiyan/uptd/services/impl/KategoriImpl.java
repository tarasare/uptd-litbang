package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.Kategori;
import com.sopiyan.uptd.services.repositories.KategoriRepository;
import com.sopiyan.uptd.services.service.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Created by Sopiyan on 10/02/2017.
 */
@Service
public class KategoriImpl implements KategoriService {
    @Autowired
    private KategoriRepository kategoriRepository;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public Kategori simpan(Kategori kategori) {
        return kategoriRepository.save(kategori);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public Kategori perbarui(Kategori kategori) {
        return kategoriRepository.save(kategori);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void hapus(String id) {
        kategoriRepository.delete(id);
    }

    @Override
    public Page<Kategori> tampilkanSemua(Pageable page) {
        return kategoriRepository.findAll(page);
    }

    @Override
    public Kategori tampilkanBerdasarkanID(String id) {
        return kategoriRepository.findOne(id);
    }

    @Override
    public Kategori tampilkanBerdasarKanNamaKategori(String namaKategori) {
        return kategoriRepository.findOneByNamaKategoriIgnoreCase(namaKategori);
    }
}
