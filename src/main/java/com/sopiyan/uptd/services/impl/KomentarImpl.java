package com.sopiyan.uptd.services.impl;

import com.sopiyan.uptd.entities.entity.Komentar;
import com.sopiyan.uptd.entities.entity.Produk;
import com.sopiyan.uptd.services.repositories.KomentarRepository;
import com.sopiyan.uptd.services.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by sopiyan on 07/03/2017.
 */
@Service
public class KomentarImpl implements KomentarService{
    @Autowired
    private KomentarRepository komentarRepository;
    @Override
    public Komentar simpan(Komentar komentar) {
        return komentarRepository.save(komentar);
    }

    @Override
    public void hapus(String id) {
        Komentar k= komentarRepository.findOne(id);
        if(k != null){
            komentarRepository.delete(id);
        }
    }

    @Override
    public Page<Komentar> tampilkanSemuaKomentarBerdasarkanProduk(Produk produk, Pageable page) {
        return komentarRepository.findByProdukOrderByTglKomentarDesc(produk, page);
    }

    @Override
    public Page<Komentar> komentarTerbaru(Pageable page) {
        return komentarRepository.findTop10ByOrderByTglKomentarDesc(page) ;
    }

    @Override
    public Komentar tampilkanBerdasarkanID(String id) {
        return komentarRepository.findOne(id);
    }
	@Override
	public Long hitungKomentarBerdasarkanProduk(Produk produk){
		return komentarRepository.countByProduk(produk);
	}
}
