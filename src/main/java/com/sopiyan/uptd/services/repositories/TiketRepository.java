package com.sopiyan.uptd.services.repositories;

import com.sopiyan.uptd.entities.entity.Tiket;
import com.sopiyan.uptd.utils.enumeration.StatusTiket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sopiyan on 10/02/2017.
 */
@Repository
public interface TiketRepository extends PagingAndSortingRepository<Tiket, String> {
    Page<Tiket> findByStatusTiketOrderByTglTiketDesc(StatusTiket statusTiket, Pageable page);
    Tiket findOneByKodeTiket(String kodeTiket);
    Page<Tiket> findByJudulTiketContainingOrderByTglTiketDesc(String judulTiket, Pageable page);
}
