package com.project.geomin.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
    @PostMapping("/compileAndRun")
    public String compileAndRun(@RequestParam("code") String code,@RequestParam("user_id") String user_id , @RequestParam("homework_num") String hn) throws IOException, InterruptedException {
        // 사용자가 입력한 코드 경로
        String directoryPath =user_id+"/"+hn+"/";
        System.out.println("user_id : " + user_id + "homework_num : " +hn);
        System.out.println("code : " +code);

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
        
        try (FileWriter fileWriter = new FileWriter("/home/ubuntu/"+user_id+"/"+hn+"/Solution.java")) {
            fileWriter.write(code);
        } catch(IOException e) {
            System.out.println("파일생성실패 : " + e.getMessage());
            return "파일생성실패: " + e.getMessage();
        }

        // javac 명령어를 사용하여 코드를 컴파일
        Process compileProcess = Runtime.getRuntime().exec("/usr/bin/javac /home/ubuntu/"+user_id+"/"+hn+"/"+"Solution.java");	
        int exitCode = compileProcess.waitFor(); // 프로세스가 종료될 때까지 대기
        System.out.println("컴파일 종료 코드: " + exitCode);

        if (exitCode == 0) { // 컴파일이 정상적으로 종료된 경우
               
        	
            // java 명령어를 사용하여 컴파일된 클래스 파일 실행
//            Process runProcess = Runtime.getRuntime().exec("java -cp C:\\\\Users\\\\user\\\\Desktop Solution");
            Process runProcess = Runtime.getRuntime().exec("/usr/bin/java /home/ubuntu/"+user_id+"/"+hn+"/"+"Solution.java");
            
            // 프로세스의 출력을 읽는 스레드 생성
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
            	System.out.println(errorLine);
            }
            errorReader.close();
            StreamGobbler outputGobbler = new StreamGobbler(runProcess.getInputStream());

            // 스레드 시작
            outputGobbler.start();

            // 프로세스가 종료될 때까지 대기
            int exitRunCode = runProcess.waitFor();
            System.out.println("실행 종료 코드: " + exitRunCode);

            // 출력 스레드의 결과 가져오기
            String output = outputGobbler.getResult();
            System.out.println(output);

            System.out.println("파일 생성 확인: " + directoryPath);
            return output;
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