package com.example.springbootdemo.system.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author fly
 * 通过第三方工具 swagger2 生成在线 api 文档,
 * swagger2 需要依赖两个jar包,
 *  1. springfox-swagger2
 *  2. springfox-swagger-ui
 * swagger2 通过注解来生成文档
 * @Api： 修饰整个类，用于描述 Controller 类。
 * @ApiOperation：描述类的方法，或者说一个接口 。
 * @ApiParam： 单个参数描述。
 * @ApiModel：用对象来接收参数。
 * @ApiProperty：用对象接收参数时，描述对象的一个字段。
 * @ApiResponse: HTTP 响应的一个描述。
 * @ApiResponses: HTTP 响应的整体描述。
 * @Apilgnore：使用该注解，表示 Swagger2 忽略这个 API。
 * @ApiError ： 发生错误返回的信息。
 * @ApiParamlmplicit： 一个请求参数。
 * @ApiParamsimplicit： 多个请求参数。
 * 需要手动将swagger的页面拷在自己项目里,static/dist下的所有文件
 * 在浏览器上访问 http://localhost:8080/swagger/index.html，浏览器就会显示在线文档的界面，
 */
@Configuration
@EnableSwagger2
public class SwaggerApi{

    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootdemo.view"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 文档页面信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("项目文档")
                .description("restful接口文档")
                .version("1.0")
                .build();
    }

}