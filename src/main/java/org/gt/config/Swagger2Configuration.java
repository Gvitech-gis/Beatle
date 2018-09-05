package org.gt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by chendai on 2018/3/21.
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()) .select() //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("org.gt.controller"))
                .paths(PathSelectors.any()) .build();


    }
    //构建api文档的详细信息函数
    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                //页面标题
//                .title("springBoot测试使用Swagger2构建RESTful API")
//                //创建人
//                .contact(new Contact("chendai","http://www.baidu.com",""))
//                //版本号
//                .version("1.0")
//                //描述
//                .description("API 描述")
//                .build();
        ApiInfo apiInfo = new ApiInfo("XXX系统数据接口文档",
                "文档描述。。。",
                "1.0.0",
                "API TERMS URL",
                "联系人邮箱",
                "license",
                "license url");
        return apiInfo;
    }
}