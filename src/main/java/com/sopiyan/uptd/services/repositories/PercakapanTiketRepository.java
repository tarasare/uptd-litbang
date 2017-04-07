package com.sopiyan.uptd.services.repositories;

import com.sopiyan.uptd.entities.entity.PercakapanTiket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sopiyan on 10/02/2017.
 */
@Repository
public interface PercakapanTiketRepository extends PagingAndSortingRepository<PercakapanTiket, String>{
}
