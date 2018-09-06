package org.gt.config;

import org.gt.bean.ApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ApiConfiguration {

    @Autowired
    private ApiProperties apiProperties;

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()) .select() //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("org.gt.controller.api"))
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
        ApiInfo apiInfo = new ApiInfo(apiProperties.getTitle(),
                apiProperties.getDescription(),
                apiProperties.getVersion(),
                apiProperties.getUrl(),
                apiProperties.getAuthor(),
                apiProperties.getLicense(),
                apiProperties.getLicenseUrl());
        return apiInfo;
    }
}