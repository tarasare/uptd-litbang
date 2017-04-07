package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.Kategori;
import com.sopiyan.uptd.entities.entity.Produk;
import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.repositories.ProdukRepository;
import com.sopiyan.uptd.services.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Sopiyan on 13/02/2017.
 */
@Service
public class ProdukImpl implements ProdukService {
    @Autowired
    private ProdukRepository produkRepository;
    @Override
    public Produk simpan(Produk produk) {
        return produkRepository.save(produk);
    }

    @Override
    public Produk perbarui(Produk produk) {
        return produkRepository.save(produk);
    }

    @Override
    public void hapus(String id) {
        produkRepository.delete(id);
    }

    @Override
    public Page<Produk> tampilkanSemua(Pageable page) {
        return produkRepository.cariSemuaUrutBerdasarkanTanggalUpdate(page);
    }

    @Override
    public Page<Produk> tampilkanBerdasarkanKategori(Kategori kategori, Pageable page) {
        return produkRepository.findByKategoriOrderByTglUpdateDesc(kategori, page);
    }

    @Override
    public Page<Produk> tampilkanBerdasarkanNamaProduk(String namaProduk, Pageable page) {
        return produkRepository.findByNamaProdukContainingOrderByTglUpdateDesc(namaProduk, page);
    }

    @Override
    public Produk tampilkanBerdasarkanKodeProduk(String kodeProduk) {
        return produkRepository.findOneByKodeProduk(kodeProduk);
    }

    @Override
    public Produk tampilkanBerdasarkanID(String idProduk) {
        return produkRepository.findOne(idProduk);
    }

    @Override
    public Page<Produk> tampilkanBerdasarkanRangeHarga(BigDecimal mulai, BigDecimal sampai, Pageable page) {
        return produkRepository.findByHargaProdukBetweenOrderByTglUpdateDesc(mulai, sampai, page);
    }

    @Override
    public Page<Produk> tampilkanBerdasarkanUser(User user, Pageable page) {
        return produkRepository.findByUserOrderByTglUpdateDesc(user, page);
    }

    @Override
    public Page<Produk> tampilkanHotProduk(Pageable page) {
        return produkRepository.findTop10ByOrderByDilihatDesc(page);
    }

    @Override
    public Long hitungJumlahProduk() {
        return produkRepository.count();
    }

    @Override
    public Long hitungJumlahProdukBerdasrkanKode(String kodeProduk) {
        return produkRepository.countByKodeProdukContaining(kodeProduk);
    }
}
