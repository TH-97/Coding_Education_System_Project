package com.project.geomin.controller;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompilerController {

    @PostMapping("/compileAndRun")
    public String compileAndRun(@RequestParam("code") String code) throws IOException, InterruptedException {
        // 사용자가 입력한 코드를 파일로 저장
        String fileName = "C:\\Users\\ddd\\Desktop\\Solution.java";

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(code);
        } catch(IOException e) {
            System.out.println("파일생성실패 : " + e.getMessage());
            return "파일생성실패: " + e.getMessage();
        }

        // javac 명령어를 사용하여 코드를 컴파일
        Process compileProcess = Runtime.getRuntime().exec("javac " + fileName);
        int exitCode = compileProcess.waitFor(); // 프로세스가 종료될 때까지 대기
        System.out.println("컴파일 종료 코드: " + exitCode);

        if (exitCode == 0) { // 컴파일이 정상적으로 종료된 경우
               
        	
            // java 명령어를 사용하여 컴파일된 클래스 파일 실행
            Process runProcess = Runtime.getRuntime().exec("java -cp C:\\\\Users\\\\ddd\\\\Desktop Solution");
            
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

            System.out.println("파일 생성 확인: " + fileName);
            return output;
        } else {
            System.out.println("컴파일 오류");
            return "컴파일 오류";
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