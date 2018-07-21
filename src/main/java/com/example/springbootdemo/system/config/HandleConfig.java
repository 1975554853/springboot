package com.example.springbootdemo.system.config;


import com.example.springbootdemo.system.handle.LoginHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author fly
 */
@Configuration
public class HandleConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginHandle loginHandle;

    @Value("${file.upload}")
    private String imageUrl;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandle);
        super.addInterceptors(registry);
    }


    /**
     * 配置静态资源目录
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 虚拟路径
        registry.addResourceHandler("/image/**")
                // 本地路径
                .addResourceLocations(imageUrl);

        // 将static下的所有文件设为静态,可以直接访问
        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:static/dist/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:templates/");

        super.addResourceHandlers(registry);

    }

    /**
     * 配置首页
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        //直接访问，转发到index
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}