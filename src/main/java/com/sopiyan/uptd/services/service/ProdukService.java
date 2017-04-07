package com.sopiyan.uptd.services.service;

import com.sopiyan.uptd.entities.entity.Kategori;
import com.sopiyan.uptd.entities.entity.Produk;
import com.sopiyan.uptd.entities.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

/**
 * Created by Sopiyan on 10/02/2017.
 */
public interface ProdukService {
    Produk simpan(Produk produk);
    Produk perbarui(Produk produk);
    void hapus(String id);
    Page<Produk> tampilkanSemua(Pageable page);
    Page<Produk> tampilkanBerdasarkanKategori(Kategori kategori, Pageable page);
    Page<Produk> tampilkanBerdasarkanNamaProduk(String namaProduk, Pageable page);
    Produk tampilkanBerdasarkanKodeProduk(String kodeProduk);
    Produk tampilkanBerdasarkanID(String idProduk);
    Page<Produk> tampilkanBerdasarkanRangeHarga(BigDecimal mulai, BigDecimal sampai, Pageable page);
    Page<Produk> tampilkanBerdasarkanUser(User user, Pageable page);
    Page<Produk> tampilkanHotProduk(Pageable page);
    Long hitungJumlahProduk();
    Long hitungJumlahProdukBerdasrkanKode(String kodeProduk);
}
