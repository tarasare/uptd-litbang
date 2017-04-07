package com.sopiyan.uptd.services.service;

import com.sopiyan.uptd.entities.entity.Komentar;
import com.sopiyan.uptd.entities.entity.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by sopiyan on 06/03/2017.
 */
public interface KomentarService {
    Komentar simpan(Komentar komentar);
    void hapus(String id);
    Page<Komentar> tampilkanSemuaKomentarBerdasarkanProduk(Produk produk, Pageable page);
    Page<Komentar> komentarTerbaru(Pageable page);
    Komentar tampilkanBerdasarkanID(String id);
	Long hitungKomentarBerdasarkanProduk(Produk produk);
}
