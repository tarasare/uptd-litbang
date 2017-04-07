package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.Tiket;
import com.sopiyan.uptd.services.repositories.TiketRepository;
import com.sopiyan.uptd.services.service.TiketService;
import com.sopiyan.uptd.utils.enumeration.StatusTiket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Sopiyan on 13/02/2017.
 */
@Service
public class TiketImpl implements TiketService {
    @Autowired
    private TiketRepository tiketRepository;

    @Override
    public Tiket simpan(Tiket tiket) {
        return tiketRepository.save(tiket);
    }

    @Override
    public Tiket perbarui(Tiket tiket) {
        return tiketRepository.save(tiket);
    }

    @Override
    public void hapus(String id) {
        tiketRepository.delete(id);
    }

    @Override
    public Page<Tiket> tampilkanBerdasarkanStatus(StatusTiket statusTiket, Pageable page) {
        return tiketRepository.findByStatusTiketOrderByTglTiketDesc(statusTiket, page);
    }

    @Override
    public Tiket tampilkanBerdasarkanKodeTiket(String kodeTiket) {
        return tiketRepository.findOneByKodeTiket(kodeTiket);
    }

    @Override
    public Tiket tampilkanBerdasarkanID(String id) {
        return tiketRepository.findOne(id);
    }

    @Override
    public Page<Tiket> tampilkanBerdasarkanJudulTiket(String judulTiket, Pageable page) {
        return tiketRepository.findByJudulTiketContainingOrderByTglTiketDesc(judulTiket, page);
    }
}
