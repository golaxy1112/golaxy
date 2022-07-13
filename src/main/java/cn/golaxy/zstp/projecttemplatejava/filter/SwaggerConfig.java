package cn.golaxy.zstp.projecttemplatejava.filter;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 9:49 2021/7/29
 * @description：
 */

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        // 设置全局请求参数：请求头中添加全局参数
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("userId")
                .description("用户id")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();
        pars.add(ticketPar.build());

        // 设置全局相应参数
        //设置全局响应状态码

        boolean swaggerEnabled = true;
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("cn.golaxy.zstp.projecttemplatejava.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .globalOperationParameters(pars)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试项目接口文档")
                .description("测试项目接口文档")
                // 作者信息
                .contact(new Contact("yaojianlin", "个人主页url", "yaojianlin@golaxy.cn"))
                .version("1.0")
                .description("测试项目接口文档")
                .build();
    }
}
