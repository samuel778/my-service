package com.micro.core.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @author saml
 * @version 1.0
 * @link <a href="http://localhost:8804/doc.html">点击访问</a>
 * @date 2019-02
 * @see <a href="https://github.com/xiaoymin/Swagger-Bootstrap-UI/blob/master/README_zh.md">SwaggerBootstrapUi</a>
 * @see <a href="https://github.com/swagger-api/swagger-core/wiki/Annotations#apimodel">Annotations</a>
 */
@Configuration
public class Swagger2 {

    /**
     * swagger通过注解表明该接口会生成文档，包括接se口名、请求方法、参数、返回信息的等等。
     *
     * @return
     * @Api：修饰整个类，描述Controller的作用
     * @ApiOperation：描述一个类的一个方法，或者说一个接口
     * @ApiParam：单个参数描述
     * @ApiModel：用对象来接收参数
     * @ApiProperty：用对象接收参数时，描述对象的一个字段
     * @ApiResponse：HTTP响应其中1个描述
     * @ApiResponses：HTTP响应整体描述
     * @ApiIgnore：使用该注解忽略这个API
     * @ApiError：发生错误返回的信息
     * @ApiImplicitParam：一个请求参数
     * @ApiImplicitParams：多个请求参数
     */
    @Bean
    public Docket basicApi() {
        /**添加head参数start*/
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenPar.name("authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
        //添加head参数end
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("基础管理")
                .useDefaultResponseMessages(false)
                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //有该注解的生成doc
                .apis(RequestHandlerSelectors.basePackage("com.micro.controller.basic"))   // 自行修改为自己的包路径
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(pars) //set Header
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket appApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("业务管理").useDefaultResponseMessages(false)
                .select().apis(RequestHandlerSelectors.basePackage("com.micro.controller.business"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }
    @Bean
    public Docket financialApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("财务管理").useDefaultResponseMessages(false)
                .select().apis(RequestHandlerSelectors.basePackage("com.micro.controller.financial"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }
    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("common").useDefaultResponseMessages(false)
                .select().apis(RequestHandlerSelectors.withClassAnnotation(MyApiCommon.class))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }
    @Bean
    public Docket realmApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("realm").useDefaultResponseMessages(false)
                .select().apis(RequestHandlerSelectors.withClassAnnotation(MyApiRealm.class))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().build();
    }

}