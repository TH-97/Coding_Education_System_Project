package com.project.geomin.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.geomin.command.UserVO;
import com.project.geomin.command.WorkVO;
import com.project.geomin.student.service.WorkMapper;
import com.project.geomin.student.service.WorkService;
import com.project.geomin.user.service.UserService;


@RestController
public class APIController {
   @Autowired
   @Qualifier("userService")
   private UserService userService;
   
	@Autowired
	private WorkMapper workMapper;
   
   
    @PostMapping("/compileAndRun")
    public String compileAndRun(@RequestParam("code") String code,@RequestParam("user_id") String user_id , @RequestParam("homework_num") String hn) throws IOException, InterruptedException {
        // 사용자가 입력한 코드 경로
        String directoryPath ="homework/"+user_id+"/"+hn;
        System.out.println("user_id : " + user_id + " homework_num : " +hn);

        WorkVO hwVO = workMapper.getHomework(hn);
        //vo 가져오기;
        hwVO.getH_para1();
        hwVO.getH_para2();
        code =code.substring(0,code.lastIndexOf("}"));
        code +=  "public static void main(String[] args){\n"
        		+"System.out.println(solution("+hwVO.getH_test1()+","+hwVO.getH_test2()+"));\n"
        +"System.out.println(solution("+hwVO.getH_test3()+","+hwVO.getH_test4()+"));\n"
        +"}\n"
        + "}";
        System.out.println("code2: " +code);
        // 폴더 객체 생성
        File directory = new File(directoryPath);

        // 폴더가 존재하지 않으면 폴더 생성
        if (!directory.exists()) {
            boolean success = directory.mkdirs(); // 여러 하위 디렉토리를 생성할 경우 mkdirs()를 사용
            if (!success) {
                System.err.println("Failed to create directory!");
            }
            System.out.println("Directory created successfully.");
        }
        
        try (FileWriter fileWriter = new FileWriter("/home/ubuntu/"+directoryPath+"/Solution.java")) {
            fileWriter.write(code);
        } catch(IOException e) {
            System.out.println("파일생성실패 : " + e.getMessage());
            return "파일생성실패: " + e.getMessage();
        }

        // javac 명령어를 사용하여 코드를 컴파일
        Process compileProcess = Runtime.getRuntime().exec("/usr/bin/javac /home/ubuntu/"+directoryPath+"/Solution.java");   
        int exitCode = compileProcess.waitFor(); // 프로세스가 종료될 때까지 대기
        System.out.println("컴파일 종료 코드: " + exitCode);

        if (exitCode == 0) { // 컴파일이 정상적으로 종료된 경우
               
           //"adsdasdasdaads";
            // java 명령어를 사용하여 컴파일된 클래스 파일 실행
//            Process runProcess = Runtime.getRuntime().exec("java -cp C:\\\\Users\\\\user\\\\Desktop Solution");
            Process runProcess = Runtime.getRuntime().exec("/usr/bin/java -cp /home/ubuntu/"+directoryPath+" Solution");
            System.out.println("경로: " + "/usr/bin/java -cp /home/ubuntu/"+directoryPath+" Solution");
            // 프로세스의 출력을 읽는 스레드 생성
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
               
               System.out.println("error: " +errorLine);
               
            }
            errorReader.close();
            
            



            int a = runProcess.waitFor();
            System.out.println("런타임 종료 코드 : " +a );
            if(a == 0) {
               BufferedReader Reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
               String InputLine ="";
               String s;
               while ((s = Reader.readLine()) != null) {
                  InputLine+=s+"\n";
                  System.out.println("표준값: " +s);
                  
               }
               System.out.println("정산값 : " +InputLine);
               if(InputLine.equals(hwVO.getH_ans1()+"\n"+hwVO.getH_ans2()+"\n")) {
            	   return "테스트에 통과 하였습니다"; 
               }else {
            	   return"테스트에 실패 하였습니다. 다시 시도하여 주세요";
               }
               
               
            }else {
               System.out.println("런타임 오류");
               return "런타임 오류";
            }


        } else {
            System.out.println("컴파일 오류");
            return "컴파일 오류";
        }
    }

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
   

    
    
    
}