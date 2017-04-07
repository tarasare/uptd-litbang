package com.sopiyan.uptd.utils.helper;

import com.sopiyan.uptd.entities.dto.ArtikelDto;
import com.sopiyan.uptd.entities.entity.Artikel;
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
public abstract class ArtikelHelper {
    public static ArtikelDto convertFromArtikel(Artikel artikel){
        ArtikelDto artikelDto = new ArtikelDto();
        artikelDto.setUser(UserHelper.convertFromUser(artikel.getUser()));
        artikelDto.setIdArtikel(artikel.getIdArtikel());
        artikelDto.setKonten(artikel.getKonten());
        artikelDto.setJudulArtikel(artikel.getJudulArtikel());
        artikelDto.setThumbnail(artikel.getThumbnail());
        artikelDto.setTanggalPost(artikel.getTanggalPost());
        artikelDto.setVisitor(artikel.getVisitor());
		artikelDto.setIdArtikel(artikel.getIdArtikel());
        artikelDto.setKategoriArtikel(KategoriArtikelHelper.convertFromKategoriArtikel(artikel.getKategoriArtikel()));
        return artikelDto;
    }

    public static Page<ArtikelDto> convertFromPageArtikel(Page<Artikel> pageArtikel){
        Page<ArtikelDto> pageArtikelDto = new Page<ArtikelDto>() {
            @Override
            public int getTotalPages() {
                return pageArtikel.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return pageArtikel.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super ArtikelDto, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return pageArtikel.getNumber();
            }

            @Override
            public int getSize() {
                return pageArtikel.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return pageArtikel.getNumberOfElements();
            }

            @Override
            public List<ArtikelDto> getContent() {
                return getList(pageArtikel);
            }

            @Override
            public boolean hasContent() {
                return pageArtikel.hasContent();
            }

            @Override
            public Sort getSort() {
                return pageArtikel.getSort();
            }

            @Override
            public boolean isFirst() {
                return pageArtikel.isFirst();
            }

            @Override
            public boolean isLast() {
                return pageArtikel.isLast();
            }

            @Override
            public boolean hasNext() {
                return pageArtikel.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return pageArtikel.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return pageArtikel.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return pageArtikel.previousPageable();
            }

            @Override
            public Iterator<ArtikelDto> iterator() {
                return null;
            }
        };
        return pageArtikelDto;
    }

    private static List<ArtikelDto> getList(Page<Artikel> pageArtikel){
        List<ArtikelDto> listArtikelDto = new ArrayList<>();
        pageArtikel.getContent().stream().forEach(a -> listArtikelDto.add(convertFromArtikel(a)));
        return listArtikelDto;
    }
}
