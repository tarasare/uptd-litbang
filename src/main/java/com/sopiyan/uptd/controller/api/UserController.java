package com.sopiyan.uptd.controller.api;


import com.sopiyan.uptd.entities.dto.UserDto;
import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.impl.CurrentUser;
import com.sopiyan.uptd.services.service.UserService;
import com.sopiyan.uptd.utils.enumeration.Role;
import com.sopiyan.uptd.utils.helper.UserHelper;
import com.sopiyan.uptd.utils.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Sopiyan on 17/02/2017.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @InitBinder("userDto")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(userValidator);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
    }
    @RequestMapping(value = "/api/logged", method = RequestMethod.GET)
    public ResponseEntity cekSession(){
            return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/public/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> lihatUserBerdasarkanid(@PathVariable(value = "id")String id)throws Exception{
        User user = userService.cariBerdasarkanID(id);
        if(user != null){
            return new ResponseEntity<UserDto>(UserHelper.convertFromUser(user), HttpStatus.OK);
        }else {
            return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
        }
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDto> login(Authentication authentication){
        try{
            User user = ((CurrentUser)authentication.getPrincipal()).getUser();
            return new ResponseEntity(UserHelper.convertFromUser(user),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/daftar", method = RequestMethod.POST)
    public ResponseEntity<UserDto> daftarUserBaru(@Valid @RequestBody UserDto userDto){
        try{
            User user = new User();
            user.setAlamat(userDto.getAlamat());
            user.setGender(userDto.getGender());
            user.setEmail(userDto.getEmail());
            user.setNamaLengkap(userDto.getNamaLengkap());
            user.setNoTelp(userDto.getNoTelp());
            user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
            user.setRole(Role.ROLE_DEFAULT);
            user.setTglJoin(new Date());
            user.setPhoto(userDto.getPhoto() != null?userDto.getPhoto():"/assets/img/user.jpg");
            user = userService.simpan(user);
            return new ResponseEntity<UserDto>(UserHelper.convertFromUser(user), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/public/user", method = RequestMethod.GET)
    public ResponseEntity<Page<UserDto>> tampilkanUser(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                       @RequestParam(name = "cari", required = false) String cari
                                                       ){
        if(page < 0){
            page = 0;
        }
        try{
            Page<User> pageUser;
            if(cari != null){
                pageUser = userService.tampilkanBerdasarkanNamaLengkap(cari, new PageRequest(page, size));
                return new ResponseEntity<Page<UserDto>>(UserHelper.convertFromPageUser(pageUser), HttpStatus.OK);
            }else {
                pageUser = userService.tampilkanSemua(new PageRequest(page, size));
                return new ResponseEntity<Page<UserDto>>(UserHelper.convertFromPageUser(pageUser), HttpStatus.OK);
            }
        }catch (Exception es){
            es.printStackTrace();
            return new ResponseEntity<Page<UserDto>>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/dashboard/user/{id}", method = RequestMethod.GET)
    public ResponseEntity gantiRoleUser(@PathVariable(value = "id")String idUser,@RequestParam(value = "status", required = true,defaultValue = "ROLE_DEFAULT")Role role){
        try {
            User user = userService.cariBerdasarkanID(idUser);
            if(user != null){
                user.setRole(role);
                user = userService.perbarui(user);
                return new ResponseEntity(HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
