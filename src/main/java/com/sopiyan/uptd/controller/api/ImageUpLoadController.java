package com.sopiyan.uptd.controller.api;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Sopiyan on 24/02/2017.
 */
@RestController
public class ImageUpLoadController {
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/secure/upload/img", method = RequestMethod.POST)
    public ResponseEntity<HashMap> uploadGambar(@RequestParam(value = "img", required = true) MultipartFile img) {
        try {
            if(!(img.getContentType().equals("image/jpg")
                    || img.getContentType().equals("image/jpeg")
                    || img.getContentType().equals("image/png")) || img.getSize() > 1000000 || img.isEmpty()){
                return new ResponseEntity<HashMap>(HttpStatus.BAD_REQUEST);
            }else{
                String namaAsli = img.getOriginalFilename();
                System.out.println(img.getContentType());;
                String homeFolder = System.getProperty("user.home");
                String lokasiTujuan = homeFolder + File.separator + "sopiyan" + File.separator + "uptd" + File.separator + "img";
                File folderTujuan = new File(lokasiTujuan);
                if (!folderTujuan.exists()) {
                    folderTujuan.mkdirs();
                }
                String nama = createNama() + namaAsli;
                File tujuan = new File(lokasiTujuan + File.separator + nama);
                img.transferTo(tujuan);
                HashMap map = new HashMap();
                map.put("location", "/upload/img/" + nama);
                return new ResponseEntity<HashMap>(map, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<HashMap>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    private String createNama() {
        String formatImage = "UPTD";
        DateTime dateTime = new DateTime();
        int tgl = dateTime.getDayOfMonth();
        int bln = dateTime.getMonthOfYear();
        int thn = dateTime.getYear();
        int jam = dateTime.getHourOfDay();
        int minute = dateTime.getMinuteOfHour();
        int detik = dateTime.getSecondOfMinute();
        int msecond = dateTime.getMillisOfSecond();
        String tanggal = (tgl < 10) ? "0" + tgl : tgl + "";
        String bulan = (bln < 10) ? "0" + bln : tgl + "";
        String kode = formatImage + msecond + detik + minute + jam + tanggal + bulan + thn;
        return kode;
    }

}
