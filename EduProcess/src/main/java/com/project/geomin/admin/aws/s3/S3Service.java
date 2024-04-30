package com.project.geomin.admin.aws.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class S3Service {
    @Value("${aws_access_key_id}")
    private String aws_access_key_id;
    @Value("${aws_secret_access_key}")
    private String aws_secret_access_key;
    @Value("${aws_target_bucket}")
    private String aws_target_bucket;

    public void putS3Object(String objectKey, byte[] objectdata, String contentType) { //클라이언트에서 입력받은 파일데이터를 올림
        try {
            System.out.println("넣기 버튼 활성");
            //여기가 권한 생성
            AwsBasicCredentials credentials = AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);
            Region region = Region.AP_NORTHEAST_2; //리전

            //권한 부여 하기
            S3Client s3 = S3Client.builder()
                    .credentialsProvider(StaticCredentialsProvider.create(credentials))
                    .region(region)
                    .build();

            //버킷 설정
            Map<String, String> metadata = new HashMap<>();
            metadata.put("x-amz-meta-myVal", "test");
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(aws_target_bucket)
                    .key(objectKey)
                    //컨텐트 타입이 없으면 다운로드 된다
                    .contentType(contentType)
                    .metadata(metadata)
                    .build();

            // s3.putObject(putOb, RequestBody.fromFile(new File(objectPath))); //로컬파일을 읽어서 올리는 경우는 이렇게
            s3.putObject(putOb, RequestBody.fromBytes(objectdata)); //클라이언트에서 입력받은 파일의 바이트데이터





        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            //System.exit(1); //프로그램 종료
        }
    }
    public void delete(String con_nm) {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);
        Region region = Region.AP_NORTHEAST_2; // 사용하는 리전으로 변경
        S3Client s3 = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(region)
                .build();

        ArrayList<ObjectIdentifier> keys = new ArrayList<>(); // 객체 키를 위한 String 리스트 사용

        try {
            //prefix를 이용하여 특정 폴더 가져오기
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .prefix(con_nm + "/")
                    .bucket(aws_target_bucket)
                    .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            List<S3Object> objects = res.contents();

            for (S3Object myValue : objects) {
                System.out.println("객체 삭제: " + myValue.key());
                ObjectIdentifier objectId = ObjectIdentifier.builder()
                        .key(myValue.key())
                        .build();
                keys.add(objectId);
            }

            DeleteObjectsRequest multiObjectDeleteRequest = DeleteObjectsRequest.builder()
                    .bucket(aws_target_bucket)
                    .delete(Delete.builder().objects(keys).build())
                    .build();

            s3.deleteObjects(multiObjectDeleteRequest);

            System.out.println("객체 삭제 완료!");

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
