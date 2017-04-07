package com.sopiyan.uptd.utils.helper;

import com.sopiyan.uptd.entities.dto.KomentarDto;
import com.sopiyan.uptd.entities.entity.Komentar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sopiyan on 07/03/2017.
 */
public abstract class KomentarHelper {

    public static KomentarDto converFromKomentar(Komentar komentar) throws Exception {
        KomentarDto komentarDto = new KomentarDto();
        komentarDto.setTglKomentar(komentar.getTglKomentar());
        komentarDto.setIsiKomentar(komentar.getIsiKomentar());
        komentarDto.setIdKomentar(komentar.getIdKomentar());
        komentarDto.setProduk(ProdukHelper.convertFromProduk(komentar.getProduk()));
        komentarDto.setUser(UserHelper.convertFromUser(komentar.getUser()));
        return komentarDto;
    }

    public static Page<KomentarDto> convertFromPageKomentar(Page<Komentar> pageKomentar) throws Exception {
        Page<KomentarDto> pageKomentarDto = new Page<KomentarDto>() {
            @Override
            public int getTotalPages() {
                return pageKomentar.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return pageKomentar.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super KomentarDto, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return pageKomentar.getNumber();
            }

            @Override
            public int getSize() {
                return pageKomentar.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return pageKomentar.getNumberOfElements();
            }

            @Override
            public List<KomentarDto> getContent() {
                return getListKomentar(pageKomentar);
            }

            @Override
            public boolean hasContent() {
                return pageKomentar.hasContent();
            }

            @Override
            public Sort getSort() {
                return pageKomentar.getSort();
            }

            @Override
            public boolean isFirst() {
                return pageKomentar.isFirst();
            }

            @Override
            public boolean isLast() {
                return pageKomentar.isLast();
            }

            @Override
            public boolean hasNext() {
                return pageKomentar.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return pageKomentar.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return pageKomentar.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return pageKomentar.previousPageable();
            }

            @Override
            public Iterator<KomentarDto> iterator() {
                return null;
            }
        };
        return pageKomentarDto;
    }

    private static List<KomentarDto> getListKomentar(Page<Komentar> komentarPage) {
        List<KomentarDto> listKomentar = new ArrayList<KomentarDto>();
        for (Komentar k : komentarPage.getContent()) {
            try {
                listKomentar.add(converFromKomentar(k));
            }catch (Exception e){

            }
        }
        return listKomentar;
    }
}