package com.sopiyan.uptd.services.service;

import com.sopiyan.uptd.entities.entity.Kategori;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Sopiyan on 10/02/2017.
 */
public interface KategoriService {

    Kategori simpan(Kategori kategori);
    Kategori perbarui(Kategori kategori);
    void hapus(String id);
    Page<Kategori> tampilkanSemua(Pageable page);
    Kategori tampilkanBerdasarkanID(String id);
    Kategori tampilkanBerdasarKanNamaKategori(String namaKategori);
}
