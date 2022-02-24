package com.hdd.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.DocumentType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger2配置类
 * @author hedd
 * @create 2021/4/21
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket creatRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hdd.server.controller"))
                .paths(PathSelectors.any())
                .build()
                //给swagger获取授权
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());

    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("云e办接口文档")
                .description("云e办接口文档")
                .contact(new Contact("xxx","http:localhost:8081/doc.html","xxx@xxx.com"))
                .version("1.0")
                .build();

    }
    private List<SecurityScheme> securitySchemes(){
        List<SecurityScheme> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization","Authorization","Header");
        result.add(apiKey);
        return result;
    }
    private List<SecurityContext> securityContexts(){
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/hello/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();

    }

    private List<SecurityReference> defultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        //授权范围
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }
}
