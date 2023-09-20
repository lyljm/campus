package xhu.click.file.service;

import cn.hutool.extra.spring.SpringUtil;
import io.minio.*;
import io.minio.errors.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xhu.click.common.entity.enums.ResultCode;
import xhu.click.common.exception.BusinessException;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@Getter
public class MinioService {

    private final String bucket;

    private final String url;

    private final MinioClient minioClient;

    public MinioService(@Value("${minio.url}") String url,
                        @Value("${minio.access}") String access,
                        @Value("${minio.secret}") String secret,
                        @Value("${minio.bucket}") String bucket) throws Exception {
        this.bucket = bucket;
        this.url = url;
        minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(access, secret)
                .build();
        // 初始化Bucket
        initBucket();
    }

    private void initBucket() throws Exception {
        // 应用启动时检测Bucket是否存在
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        // 如果Bucket不存在，则创建Bucket
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            log.info("成功创建 Bucket [{}]", bucket);
        }
    }

    /**
     * 文件上传，指定文件名
     *
     * @param path       路径
     * @param objectName 文件名
     * @param file       文件
     */
    public void putObject(String path, String objectName, MultipartFile file) {
        upload(file, path + objectName);
    }

    /**
     * 文件上传,文件名随机
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String putObject(String path, MultipartFile file) {
        // 获取文件后缀名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // 为了避免文件名重复，使用UUID重命名文件，将横杠去掉
        String fileName = path + UUID.randomUUID().toString().replace("-", "") + "." + extension;
        upload(file, fileName);
        return url + bucket + "/" + fileName;
    }


    private void upload(MultipartFile file, String PathAndName) {
        //获取文件大小
        long size = file.getSize();
        //文件类型
        String contentType = file.getContentType();
        try {
            //文件流
            InputStream inputStream = file.getInputStream();
            long start = System.currentTimeMillis();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(PathAndName)
                    .contentType(contentType)
                    .stream(inputStream, size, 1024 * 1024 * 10)
                    .build());
            log.info("成功上传文件至云端 [{}],文件大小[{}]，耗时 [{} ms]", PathAndName, size, System.currentTimeMillis() - start);
            inputStream.close();
        } catch (Exception e) {
            log.error("文件上传失败 [{}],文件大小[{}]\n,{}", PathAndName, size, e.getMessage());
            throw new BusinessException(ResultCode.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * 获取文件流
     *
     * @param pathAndName 对象（文件）路径和文件名
     * @return 文件流
     */
    public GetObjectResponse getObject(String pathAndName) {
        long start = System.currentTimeMillis();
        GetObjectResponse response = null;
        try {
            response = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(pathAndName)
                    .build());
            log.info("成功获取 Object [{}]，耗时 [{} ms]", pathAndName, System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.error("获取 Object 失败 ：[{}] ，耗时 [{} ms]\n {}", pathAndName, System.currentTimeMillis() - start, e.getMessage());
            throw new BusinessException(ResultCode.FILE_DOWNLOAD_ERROR);
        }
        return response;
    }

    /**
     * 删除对象（文件）
     *
     * @param pathAndName 对象（文件）路径和文件名
     */
    public void removeObject(String pathAndName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(pathAndName)
                    .build());
        } catch (Exception e) {
            log.error("删除 Object 失败： [{}] \n{}", pathAndName, e.getMessage());
            throw new BusinessException(ResultCode.FILE_DELETE_ERROR);
        }
        log.info("成功删除 Object [{}]", pathAndName);
    }
}
