package com.sopiyan.uptd.utils.helper;

import com.sopiyan.uptd.entities.dto.KomentarArtikelDto;
import com.sopiyan.uptd.entities.entity.KomentarArtikel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sopiyan on 16/03/2017.
 */
public abstract class KomentarArtikelHelper {
    public static KomentarArtikelDto convertFromKometarArtikel(KomentarArtikel komentarArtikel){
        KomentarArtikelDto komentarArtikelDto = new KomentarArtikelDto();
        komentarArtikelDto.setUser(UserHelper.convertFromUser(komentarArtikel.getUser()));
        komentarArtikelDto.setIdKomentar(komentarArtikel.getIdKomentar());
        komentarArtikelDto.setIsiKomentar(komentarArtikel.getIsiKomentar());
        komentarArtikelDto.setTglKomentar(komentarArtikel.getTglKomentar());
//        komentarArtikelDto.setArtikel(ArtikelHelper.convertFromArtikel(komentarArtikel.getArtikel()));
        return komentarArtikelDto;
    }
    public static Page<KomentarArtikelDto> convertFromPageKomentarArtikel(Page<KomentarArtikel> pageKomentarArtikel){
        Page<KomentarArtikelDto> pageKomentarArtikelDto = new Page<KomentarArtikelDto>() {
            @Override
            public int getTotalPages() {
                return pageKomentarArtikel.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return pageKomentarArtikel.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super KomentarArtikelDto, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return pageKomentarArtikel.getNumber();
            }

            @Override
            public int getSize() {
                return pageKomentarArtikel.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return pageKomentarArtikel.getNumberOfElements();
            }

            @Override
            public List<KomentarArtikelDto> getContent() {
                return getListKomentarArtikelDto(pageKomentarArtikel);
            }

            @Override
            public boolean hasContent() {
                return pageKomentarArtikel.hasContent();
            }

            @Override
            public Sort getSort() {
                return pageKomentarArtikel.getSort();
            }

            @Override
            public boolean isFirst() {
                return pageKomentarArtikel.isFirst();
            }

            @Override
            public boolean isLast() {
                return pageKomentarArtikel.isLast();
            }

            @Override
            public boolean hasNext() {
                return pageKomentarArtikel.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return pageKomentarArtikel.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return pageKomentarArtikel.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return pageKomentarArtikel.previousPageable();
            }

            @Override
            public Iterator<KomentarArtikelDto> iterator() {
                return null;
            }
        };
        return pageKomentarArtikelDto;
    }

    private static List<KomentarArtikelDto> getListKomentarArtikelDto(Page<KomentarArtikel> pageKomentarArtikel){
        List<KomentarArtikelDto> listKomentarArtikelDto = new ArrayList<KomentarArtikelDto>();
        pageKomentarArtikel.getContent().stream().forEach(k -> listKomentarArtikelDto.add(convertFromKometarArtikel(k)));
        return listKomentarArtikelDto;
    }
}
