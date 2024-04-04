package com.project.geomin.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class adminRestController {

    @PostMapping("/cloudUpload")
    public void cloudUpload(@RequestParam("file_data") List<MultipartFile> file,
                            @RequestParam("con_nm") String con_nm,
                            @RequestParam("cate_no") String cate_no,
                            @RequestParam("con_price") String con_price,
                            @RequestParam("con_discount") String con_discount,
                            @RequestParam("con_description") String con_description,
                            @RequestParam("cate_lv") String cate_lv
                            ){
        System.out.println(file.toString());
        System.out.println(con_nm);
        System.out.println(cate_no);
        System.out.println(con_price);
        System.out.println(con_discount);
        System.out.println(con_description);
        System.out.println(cate_lv);

    }
}
