package org.mystic.blog.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/22 8:59
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 配置可以被跨域的路径
                .addMapping("/")
                // 配置可以请求的类型
                .allowedMethods("*")
                // 配置可以访问我们的域名
                .allowedOrigins("*")
                // 配置可以访问的请求头信息
                .allowedHeaders("*");
    }
}
