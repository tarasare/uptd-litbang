package com.sopiyan.uptd.services.repositories;

import com.sopiyan.uptd.entities.entity.Artikel;
import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import com.sopiyan.uptd.entities.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sopiyan on 10/02/2017.
 */
@Repository
public interface ArtikelRepository extends PagingAndSortingRepository<Artikel, String> {
    Page<Artikel> findByJudulArtikelContainingOrderByTanggalPostDesc(String judul, Pageable page);
    Page<Artikel> findByKategoriArtikelOrderByTanggalPostDesc(KategoriArtikel kategoriArtikel, Pageable page);
    Page<Artikel> findByUserOrderByTanggalPostDesc(User user, Pageable page);
    Page<Artikel> findTop10ByOrderByVisitorDesc(Pageable page);
}
