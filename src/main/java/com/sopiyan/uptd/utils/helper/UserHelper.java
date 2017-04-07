package com.sopiyan.uptd.utils.helper;

import com.sopiyan.uptd.entities.dto.UserDto;
import com.sopiyan.uptd.entities.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sopiyan on 14/02/2017.
 */
public abstract class UserHelper {
    public static UserDto convertFromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setPhoto(user.getPhoto());
        userDto.setIdUser(user.getIdUser());
        userDto.setGender(user.getGender());
        userDto.setAlamat(user.getAlamat());
        userDto.setEmail(user.getEmail());
        userDto.setNamaLengkap(user.getNamaLengkap());
        userDto.setNoTelp(user.getNoTelp());
        userDto.setTglJoin(user.getTglJoin());
        userDto.setRole(user.getRole());
        return userDto;
    }
    public static Page<UserDto> convertFromPageUser(Page<User> pageUser){
        Page<UserDto> pageUserDto = new Page<UserDto>() {
            @Override
            public int getTotalPages() {
                return pageUser.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return pageUser.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super UserDto, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return pageUser.getNumber();
            }

            @Override
            public int getSize() {
                return pageUser.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return pageUser.getNumberOfElements();
            }

            @Override
            public List<UserDto> getContent() {
                return getListUserDto(pageUser);
            }

            @Override
            public boolean hasContent() {
                return pageUser.hasContent();
            }

            @Override
            public Sort getSort() {
                return pageUser.getSort();
            }

            @Override
            public boolean isFirst() {
                return pageUser.isFirst();
            }

            @Override
            public boolean isLast() {
                return pageUser.isLast();
            }

            @Override
            public boolean hasNext() {
                return pageUser.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return pageUser.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return pageUser.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return pageUser.previousPageable();
            }

            @Override
            public Iterator<UserDto> iterator() {
                return null;
            }
        };
        return pageUserDto;
    }
    private static List<UserDto> getListUserDto(Page<User> pageUser){
        List<UserDto> listUserDto = new ArrayList<>();
        pageUser.getContent().stream().forEach(u -> listUserDto.add(convertFromUser(u)));
        return listUserDto;
    }
}

