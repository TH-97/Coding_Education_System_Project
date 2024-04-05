package com.project.geomin.admin;

import com.project.geomin.admin.aws.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class adminRestController {
    @Autowired
    S3Service s3Service;

    @PostMapping("/cloudUpload")
    public void cloudUpload(@RequestParam("file_data") List<MultipartFile> file,
                            @RequestParam("con_nm") String con_nm,
                            @RequestParam("cate_no") String cate_no,
                            @RequestParam("con_price") String con_price,
                            @RequestParam("con_discount") String con_discount,
                            @RequestParam("con_description") String con_description,
                            @RequestParam("cate_lv") String cate_lv,
                            @RequestParam("file_data_name") List<String> file_data_name
                            ){
        System.out.println(file.toString());
        System.out.println(file_data_name.toString());
        System.out.println(con_nm);
        System.out.println(cate_no);
        System.out.println(con_price);
        System.out.println(con_discount);
        System.out.println(con_description);
        System.out.println(cate_lv);

        try {
            for(int i = 0; i < file.size(); i++) {
//            String objectKey = file.getOriginalFilename(); //파일명
                String objectKey = i + file_data_name.get(i); //여기서 이름을 이정할 수 있다
                System.out.println(objectKey);
                //여기가 컨탠츠 이름
                String contentName = con_nm; //컨텐츠 이름
                //
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
