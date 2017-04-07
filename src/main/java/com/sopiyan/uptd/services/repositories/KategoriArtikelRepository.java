package com.sopiyan.uptd.services.repositories;

import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sopiyan on 10/02/2017.
 */
@Repository
public interface KategoriArtikelRepository extends PagingAndSortingRepository<KategoriArtikel, String> {
    KategoriArtikel findOneByNamaKategoriArtikel(String nama);
}
