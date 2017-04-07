package com.sopiyan.uptd.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;

/**
 * Created by Sopiyan on 14/02/2017.
 */
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
        //agar file yang di upload di home folder  bisa diakses
        String homeFolder = System.getProperty("user.home");
        String lokasiTujuan ="file:///"+homeFolder + File.separator + "sopiyan" + File.separator + "uptd" + File.separator +"img" +File.separator;
        registry.addResourceHandler("/upload/img/**").addResourceLocations(lokasiTujuan).setCachePeriod(0);
    }
}
