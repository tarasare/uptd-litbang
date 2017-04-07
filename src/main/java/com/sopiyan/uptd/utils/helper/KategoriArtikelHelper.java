package com.sopiyan.uptd.utils.helper;

import com.sopiyan.uptd.entities.dto.KategoriArtikelDto;
import com.sopiyan.uptd.entities.entity.KategoriArtikel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * Created by Sopiyan on 17/02/2017.
 */
public abstract class KategoriArtikelHelper {
public static KategoriArtikelDto convertFromKategoriArtikel(KategoriArtikel kategoriArtikel){
    KategoriArtikelDto kategoriArtikelDto = new KategoriArtikelDto();
    kategoriArtikelDto.setDeskripsiKategoriArtikel(kategoriArtikel.getDeskripsiKategoriArtikel());
    kategoriArtikelDto.setIdKategoriArtikel(kategoriArtikel.getIdKategoriArtikel());
    kategoriArtikelDto.setNamaKategoriArtikel(kategoriArtikel.getNamaKategoriArtikel());
    return kategoriArtikelDto;
}
    public static Page<KategoriArtikelDto> convertFromPageKategoriArtikel(Page<KategoriArtikel> pageKategoriArtikel){
        Page<KategoriArtikelDto> pageKategoriArtikelDto = new Page<KategoriArtikelDto>() {
            @Override
            public int getTotalPages() {
                return pageKategoriArtikel.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return pageKategoriArtikel.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super KategoriArtikelDto, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return pageKategoriArtikel.getNumber();
            }

            @Override
            public int getSize() {
                return pageKategoriArtikel.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return pageKategoriArtikel.getNumberOfElements();
            }

            @Override
            public List<KategoriArtikelDto> getContent() {
                return getListKategoriArtikelDto(pageKategoriArtikel);
            }

            @Override
            public boolean hasContent() {
                return pageKategoriArtikel.hasContent();
            }

            @Override
            public Sort getSort() {
                return pageKategoriArtikel.getSort();
            }

            @Override
            public boolean isFirst() {
                return pageKategoriArtikel.isFirst();
            }

            @Override
            public boolean isLast() {
                return pageKategoriArtikel.isLast();
            }

            @Override
            public boolean hasNext() {
                return pageKategoriArtikel.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return pageKategoriArtikel.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return pageKategoriArtikel.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return pageKategoriArtikel.previousPageable();
            }

            @Override
            public Iterator<KategoriArtikelDto> iterator() {
                return null;
            }
        };
        return pageKategoriArtikelDto;
    }

    private static List<KategoriArtikelDto> getListKategoriArtikelDto(Page<KategoriArtikel> pageKategoriArtikel){
        List<KategoriArtikelDto> listKategoriArtikelDto = new ArrayList<KategoriArtikelDto>();
        pageKategoriArtikel.getContent().stream().forEach(k -> listKategoriArtikelDto.add(convertFromKategoriArtikel(k)));
        return listKategoriArtikelDto;
    }
}
