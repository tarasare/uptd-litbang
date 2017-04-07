package com.sopiyan.uptd.services.service;

import com.sopiyan.uptd.entities.entity.Tiket;
import com.sopiyan.uptd.utils.enumeration.StatusTiket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Sopiyan on 10/02/2017.
 */
public interface TiketService {
    Tiket simpan(Tiket tiket);
    Tiket perbarui(Tiket tiket);
    void hapus(String id);
    Page<Tiket> tampilkanBerdasarkanStatus(StatusTiket statusTiket, Pageable page);
    Tiket tampilkanBerdasarkanKodeTiket(String kodeTiket);
    Tiket tampilkanBerdasarkanID(String id);
    Page<Tiket> tampilkanBerdasarkanJudulTiket(String judulTiket, Pageable page);
}
