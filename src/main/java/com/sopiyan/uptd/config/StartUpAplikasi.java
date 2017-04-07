package com.sopiyan.uptd.config;

import com.sopiyan.uptd.entities.entity.User;
import com.sopiyan.uptd.services.service.UserService;
import com.sopiyan.uptd.utils.enumeration.Gender;
import com.sopiyan.uptd.utils.enumeration.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Sopiyan on 01/02/2017.
 */
@Component
public class StartUpAplikasi implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;
    private Logger log = LogManager.getLogger();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("aplikasi Dimulai");
        generateDefaultAdministrator();
    }

    private void generateDefaultAdministrator() {
        User user = userService.cariBerdasarkanEmail("admin@uptd.com");
        if (user == null) {
            user = new User();
            user.setEmail("admin@uptd.com");
            user.setGender(Gender.PRIA);
            user.setTglJoin(new Date());
            user.setRole(Role.ROLE_ADMIN);
            user.setAlamat("Purwakarta");
            user.setNamaLengkap("Admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setNoTelp("0859-2604-8083");
            user.setPhoto("/assets/img/user.jpg");
            userService.simpan(user);
        } else {
			user.setPhoto("/assets/img/user.jpg");
            user.setRole(Role.ROLE_ADMIN);
            userService.perbarui(user);
        }
    }
}
