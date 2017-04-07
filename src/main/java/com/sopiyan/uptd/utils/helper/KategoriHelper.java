package com.sopiyan.uptd.utils.helper;

import com.sopiyan.uptd.entities.dto.KategoriDto;
import com.sopiyan.uptd.entities.entity.Kategori;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * Created by Sopiyan on 17/02/2017.
 */
public abstract class KategoriHelper {
    public static KategoriDto convertFromKategori(Kategori kategori) {
        KategoriDto kategoriDto = new KategoriDto();
        kategoriDto.setIdKategori(kategori.getIdKategori());
        kategoriDto.setKeteraganKategori(kategori.getKeteranganKategori());
        kategoriDto.setNamaKategori(kategori.getNamaKategori());
        return kategoriDto;
    }
    public static Set<KategoriDto> convertFromSetKategori(Set<Kategori> listKategori){
        Set<KategoriDto> listKategoriDto = new HashSet<>();
        listKategori.stream().forEach(kategori -> listKategoriDto.add(convertFromKategori(kategori)));
        return listKategoriDto;
    }
    public static Page<KategoriDto> convertFromPageKategori(Page<Kategori> pageKategori)throws  Exception{
        Page<KategoriDto> pageKategoriDto = new Page<KategoriDto>() {
            @Override
            public int getTotalPages() {
                return pageKategori.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return pageKategori.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super KategoriDto, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return pageKategori.getNumber();
            }

            @Override
            public int getSize() {
                return pageKategori.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return pageKategori.getNumberOfElements();
            }

            @Override
            public List<KategoriDto> getContent() {
                return getList(pageKategori);
            }

            @Override
            public boolean hasContent() {
                return pageKategori.hasContent();
            }

            @Override
            public Sort getSort() {
                return pageKategori.getSort();
            }

            @Override
            public boolean isFirst() {
                return pageKategori.isFirst();
            }

            @Override
            public boolean isLast() {
                return pageKategori.isLast();
            }

            @Override
            public boolean hasNext() {
                return pageKategori.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return pageKategori.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return pageKategori.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return pageKategori.previousPageable();
            }

            @Override
            public Iterator<KategoriDto> iterator() {
                return null;
            }
        };
        return pageKategoriDto;
    }
    public static List<KategoriDto> getList(Page<Kategori> pageKategori){
        List<KategoriDto> listKategoriDto = new ArrayList<>();
        pageKategori.getContent().stream().forEach(k -> listKategoriDto.add(convertFromKategori(k)));
        return listKategoriDto;
    }
}
