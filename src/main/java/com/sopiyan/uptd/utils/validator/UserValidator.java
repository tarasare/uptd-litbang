package com.sopiyan.uptd.utils.validator;

import com.sopiyan.uptd.entities.dto.UserDto;
import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sopiyan on 04/04/2017.
 */
@Component
public class UserValidator implements Validator{
    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        validasiEmail(userDto, errors);
        validasiRepeatPassword(userDto, errors);
    }
    private void validasiEmail(UserDto userDto, Errors errors){
        User user = userService.cariBerdasarkanEmail(userDto.getEmail());
        if(user != null){
            if(userDto.getIdUser() != null){
                if(!user.getIdUser().equals(userDto.getIdUser())){
                    errors.reject("user.email","Email sudah digunakan");
                }
            }else {
                errors.reject("user.email","Email sudah digunakan");
            }
        }
    }
    private void validasiRepeatPassword(UserDto userDto, Errors errors){
        if(userDto.getPassword() == null || !userDto.getPassword().equals(userDto.getRepeatPassword())){
            errors.reject("user.password","Masukan dan ulangi password dengan benar");
        }
    }
}
