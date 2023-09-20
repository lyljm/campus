package xhu.click.file.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class MinioConfig {
    @Value("${minio.url}")
    String url;
    @Value("${minio.access}")
    String access;
    @Value("${minio.secret}")
    String secret;
    @Value("${minio.bucket}")
    String bucket;
    @Value("${minio.prefixUrl}")
    String prefixUrl;
}
