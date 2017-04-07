package com.sopiyan.uptd.services.repositories;

import com.sopiyan.uptd.entities.entity.Komentar;
import com.sopiyan.uptd.entities.entity.Produk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sopiyan on 07/03/2017.
 */
@Repository
public interface KomentarRepository extends PagingAndSortingRepository<Komentar, String>{
    Page<Komentar> findByProdukOrderByTglKomentarDesc(Produk produk, Pageable page);
    Page<Komentar> findTop10ByOrderByTglKomentarDesc(Pageable page);
	Long countByProduk(Produk produk);
}
