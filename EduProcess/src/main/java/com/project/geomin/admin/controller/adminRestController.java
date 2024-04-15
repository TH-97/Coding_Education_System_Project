package com.project.geomin.admin.controller;

import com.project.geomin.admin.aws.s3.S3Service;
import com.project.geomin.admin.service.AdminService;
import com.project.geomin.admin.service.AdminServiceImpl;
import com.project.geomin.command.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
public class adminRestController {
    @Autowired
    S3Service s3Service;
    @Autowired
    AdminService adminService;

    @PostMapping("/cloudUpload")
    public void cloudUpload(@RequestParam("file_data") List<MultipartFile> file,
                            @RequestParam("con_nm") String con_nm,
                            @RequestParam("cate_no") String cate_no,
                            @RequestParam("con_price") Integer con_price,
                            @RequestParam("con_discount") Integer con_discount,
                            @RequestParam("con_description") String con_description,
                            @RequestParam("con_lv") String con_lv,
                            @RequestParam("file_data_name") List<String> file_data_name
                            ){


        if (Objects.equals(cate_no, "자바")){
            cate_no = String.valueOf(1);
        } else if (Objects.equals(cate_no, "파이썬")) {
            cate_no = String.valueOf(2);
        } else {
            cate_no = String.valueOf(3);
        }

        String thumbnail =file_data_name.get(0);
        String thumbnail_path ="https://s3.ap-northeast-2.amazonaws.com/demo-hood3392.com/"+con_nm+"/"+0+file_data_name.get(0);
        String service_class ="무료";

        adminService.insertInformation(con_nm,cate_no,con_price,con_discount,con_description,con_lv,thumbnail,thumbnail_path,service_class);


        for(int i = 1; i<file_data_name.size(); i++){
            String file_nm = file_data_name.get(i);
            String file_path ="https://s3.ap-northeast-2.amazonaws.com/demo-hood3392.com/"+con_nm+"/"+i+file_data_name.get(i);
            String service_class1 ="무료";
            adminService.insertInformation2(con_nm,file_nm,file_path,service_class1);
        }


        try {
            for(int i = 0; i < file.size(); i++) {
//            String objectKey = file.getOriginalFilename(); //파일명
                String objectKey = i + file_data_name.get(i); //여기서 이름을 이정할 수 있다
                //여기가 컨탠츠 이름
                String contentName = con_nm; //컨텐츠 이름
                // 파일이름이 곂치지 않게 하기
                objectKey = contentName + "/" + objectKey.substring(objectKey.lastIndexOf("\\") + 1);

                //중복으로 올라오는 파일을 피하고싶으면, UUID객체로 랜덤값을 부여하세요.
                byte[] filedata = file.get(i).getBytes(); //파일데이터
                //파일의 컨텐츠 타입
                String contentType = file.get(i).getContentType();
                // 여기서 지정한 이름을 넣고 filedata 와 타입을 넣어준다 타입을 넣어주지 않으면 객체 url에서 힘들어 질 수 있다
                s3Service.putS3Object(objectKey, filedata, contentType);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
