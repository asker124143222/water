package com.home.water.config;

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
 * @Author: xu.dm
 * @Date: 2020/4/8 20:15
 * @Description:
 *
 *  @Api : 用在类上，说明该类的主要作用。
 *  @ApiOperation：用在方法上，给API增加方法说明。*
 *  @ApiImplicitParam：用来注解来给方法入参增加说明。
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.home.water.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("water项目API文档")
                .description("需要用restful风格")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
