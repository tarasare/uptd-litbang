package com.sopiyan.uptd.services.repositories;

import com.sopiyan.uptd.entities.entity.Artikel;
import com.sopiyan.uptd.entities.entity.KomentarArtikel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sopiyan on 17/03/2017.
 */
@Repository
public interface KomentarArtikelRepository extends PagingAndSortingRepository<KomentarArtikel, String> {
    Page<KomentarArtikel> findByArtikelOrderByTglKomentarDesc(Artikel artikel, Pageable page);
    Page<KomentarArtikel> findTop10ByOrderByTglKomentarDesc(Pageable page);
    Long countByArtikel(Artikel artikel);
}
