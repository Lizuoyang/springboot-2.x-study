package com.lizuoyang.springboot.config;

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
 * @ClassName SwaggerConfiguration
 * @Description Swagger 2 配置类
 * @Author LiZuoYang
 * @Date 2021/3/9 16:37
 **/
@Configuration
/**
 * 标记项目启用 Swagger API 接口文档
 */
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 设置API信息
                .apiInfo(this.apiInfo())
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lizuoyang.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建 API 信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试接口文档示例")
                .description("测试接口文档描述")
                .version("1.0.0")
                .contact(new Contact("lizuoyang", "http://blog.baozi.show", "lizuoyangsina@qq.com"))
                .build();
    }
}
