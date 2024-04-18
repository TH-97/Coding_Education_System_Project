package com.project.geomin.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.project.geomin.command.WorkVO;
import com.project.geomin.user.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.geomin.command.UserVO;
import com.project.geomin.user.service.UserService;


@RestController
public class APIController {
	@Autowired
	@Qualifier("userService")
	private UserService userService;




    @PostMapping("/idCheck")
    public String idCheck(@RequestParam("user_id") String id) throws IOException, InterruptedException{
    	UserVO vo = userService.checkLogin(id);
   if(id =="") {
       return "";
   }
   if(vo != null) {
	   return "중복된 아이디입니다";
   }else {
	   System.out.println("id = " + id);
	   return "사용가능한 아이디입니다";
   }
    }

    // 프로세스의 출력을 읽는 스레드 클래스
    class StreamGobbler extends Thread {
        private BufferedReader reader;
        private StringBuilder result = new StringBuilder();

        StreamGobbler(InputStream inputStream) {
            this.reader = new BufferedReader(new InputStreamReader(inputStream));
        }

        @Override
        public void run() {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String getResult() {
            return result.toString();
        }
    }

    
    
    
}