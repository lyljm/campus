package xhu.click.server.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import xhu.click.server.intercepter.LoginInterceptor;
import xhu.click.server.intercepter.RefreshTokenInterceptor;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Configuration
@EnableSwagger2 //两个注释开启api文档
@EnableKnife4j
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        /**
         * todo 修改拦截路径
         */
        registry.addInterceptor(new LoginInterceptor(stringRedisTemplate))
                .excludePathPatterns(
                        /**
                         * todo 拦截
                         */
//                        "/**",
                        "/wx/login",
                        "doc.html",
                        "/webjars/**",
                        "/swagger-resources",
                        "/v2/api-docs"
                )
                .addPathPatterns(
                        "/user"
                )
                .order(1);//值越小越先执行
        // token刷新的拦截器
        //设置执行顺序
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**").order(0);
    }

    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射...");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");

    }

    /**
     * 扩展mvc框架的消息转换器
     *
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");
        converters.add(0, new FastJsonHttpMessageConverter());
////        //创建消息转换器对象
//        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//        //设置对象转换器，底层使用Jackson将Java对象转为json
//        messageConverter.setObjectMapper(new JsonMapper());
//        //将上面的消息转换器对象追加到mvc框架的转换器集合中
//        converters.add(0, messageConverter);
////        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
////        FastJsonConfig fastJsonConfig = new FastJsonConfig();
////        fastConverter.setFastJsonConfig(fastJsonConfig);
////        converters.add(fastConverter);
    }

    //描述接口文档相关的信息
    @Bean
    public Docket createRestApi() {
        // 文档类型
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()                                                                //扫描controller相关的包
                .apis(RequestHandlerSelectors.basePackage("xhu.click"))
                .paths(PathSelectors.any())
                .build();
    }

    //描述接口文档
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("campus")
                .version("1.0")
                .description("click campus")
                .build();
    }
}