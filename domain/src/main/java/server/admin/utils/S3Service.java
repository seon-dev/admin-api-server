package server.admin.utils;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class S3Service {
    public static String bucketUrl;
    private AmazonS3 s3Client;
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.bucket.url}")
    public void setBucketUrl(String bucketUrl) {
        S3Service.bucketUrl = bucketUrl;
    }

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(String base64Image, String fileName) throws Exception {
        String[] strings = base64Image.split(",");
        // convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            outputStream.write(data, 0, data.length);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            MultipartFile multipartFile = new MockMultipartFile(fileName, inputStream);

            return upload(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String upload(MultipartFile file) throws IOException {
        return upload(file, file.getName());
    }

    public String upload(MultipartFile file, String fileName) throws IOException {
        System.out.println(file+" "+fileName);
        delete(fileName);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        s3Client.putObject(
                new PutObjectRequest(bucket + "/origin", fileName, file.getInputStream(), metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        return s3Client.getUrl(bucket, fileName).toString();
    }

    public boolean delete(String fileName) throws SdkClientException {
        // 대상 파일이 존재하지 않는 경우
        if (fileName == null) {
            return false;
        }

        Boolean isExist = s3Client.doesObjectExist(bucket + "/origin", fileName);

        if (isExist) {
            String[] paths = {"/origin", "/w_1080", "/w_750", "/w_640", "/w_480", "/w_320", "/w_240", "/w_150"};

            for (String path : paths) {
                s3Client.deleteObject(bucket + path, fileName);
            }
        }

        return isExist;
    }

    public String getResourcePath(String fileName) {
        return bucketUrl + "/origin/" + fileName;
    }

}
