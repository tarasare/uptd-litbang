package com.sopiyan.uptd.utils.helper;

import com.sopiyan.uptd.entities.dto.ProdukDto;
import com.sopiyan.uptd.entities.entity.Produk;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sopiyan on 17/02/2017.
 */
public abstract class ProdukHelper {
    public static ProdukDto convertFromProduk(Produk produk) throws Exception{
        ProdukDto produkDto = new ProdukDto();
        produkDto.setDeskripsiProduk(produk.getDeskripsiProduk());
        produkDto.setGambarProduk(produk.getGambarProduk());
        produkDto.setHargaProduk(produk.getHargaProduk());
        produkDto.setIdProduk(produk.getIdProduk());
        produkDto.setKategori(KategoriHelper.convertFromKategori(produk.getKategori()));
        produkDto.setKodeProduk(produk.getKodeProduk());
        produkDto.setDilihat(produk.getDilihat());
        produkDto.setTglUpdate(produk.getTglUpdate());
        produkDto.setNamaProduk(produk.getNamaProduk());
        produkDto.setUser(UserHelper.convertFromUser(produk.getUser()));
        return produkDto;
    }
    public static Page<ProdukDto> convertFromPageProduk(Page<Produk> pageProduk) throws Exception{
        Page<ProdukDto> pageProdukDto = new Page<ProdukDto>() {
            @Override
            public int getTotalPages() {
                return pageProduk.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return pageProduk.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super ProdukDto, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return pageProduk.getNumber();
            }

            @Override
            public int getSize() {
                return pageProduk.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return pageProduk.getNumberOfElements();
            }

            @Override
            public List<ProdukDto> getContent() {
                return ProdukHelper.getList(pageProduk);
            }

            @Override
            public boolean hasContent() {
                return pageProduk.hasContent();
            }

            @Override
            public Sort getSort() {
                return pageProduk.getSort();
            }

            @Override
            public boolean isFirst() {
                return pageProduk.isFirst();
            }

            @Override
            public boolean isLast() {
                return pageProduk.isLast();
            }

            @Override
            public boolean hasNext() {
                return pageProduk.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return pageProduk.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return pageProduk.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return pageProduk.previousPageable();
            }

            @Override
            public Iterator<ProdukDto> iterator() {
                return null;
            }
        };
        return pageProdukDto;
    }

    private static List<ProdukDto> getList(Page<Produk> pageProduk){
        List<ProdukDto> listProdukDto = new ArrayList<>();
        for(Produk p: pageProduk.getContent()){
            try {
                listProdukDto.add(ProdukHelper.convertFromProduk(p));
            }catch (Exception e){

            }
        }
        return listProdukDto;
    }
}
