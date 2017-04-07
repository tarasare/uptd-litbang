package com.sopiyan.uptd.controller.advice.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sopiyan on 24/02/2017.
 */
@ControllerAdvice
public class UploadImageErrorHandler {
    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    String ImageUploadHandlerException(HttpServletRequest request, Throwable ex){
        return "Gagal upload Gambar" + ex.getMessage();
    }
}
