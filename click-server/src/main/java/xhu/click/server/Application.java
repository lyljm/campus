package xhu.click.server;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("xhu.click.db.mapper")
@EnableCaching
@ComponentScan({"xhu.click.common","xhu.click.server","xhu.click.captcha","xhu.click.db","xhu.click.wx.login"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
