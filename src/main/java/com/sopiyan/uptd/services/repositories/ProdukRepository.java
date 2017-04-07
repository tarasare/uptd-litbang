package com.sopiyan.uptd.services.repositories;

import com.sopiyan.uptd.entities.entity.Kategori;
import com.sopiyan.uptd.entities.entity.Produk;
import com.sopiyan.uptd.entities.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by Sopiyan on 10/02/2017.
 */
@Repository
public interface ProdukRepository extends PagingAndSortingRepository<Produk, String> {
    Page<Produk> findByKategoriOrderByTglUpdateDesc(Kategori kategori, Pageable page);
    Page<Produk> findByNamaProdukContainingOrderByTglUpdateDesc(String namaProduk, Pageable page);
    Produk findOneByKodeProduk(String kodeProduk);
    Page<Produk> findByHargaProdukBetweenOrderByTglUpdateDesc(BigDecimal mulai, BigDecimal sampai, Pageable page);
    Page<Produk> findByUserOrderByTglUpdateDesc(User user, Pageable page);
    Page<Produk> findTop10ByOrderByDilihatDesc(Pageable page);
    Long countByKodeProdukContaining(String kodeProduk);
    @Query("select p from Produk p order by p.tglUpdate DESC ")
    Page<Produk> cariSemuaUrutBerdasarkanTanggalUpdate(Pageable page);
}
