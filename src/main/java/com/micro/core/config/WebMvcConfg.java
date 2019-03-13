package com.micro.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.micro.core.config.argumentresolver.DateArgumentResolver;
import com.micro.core.config.argumentresolver.PageableArgumentResolver;
import com.micro.core.config.swagger2.Swagger2;
import com.micro.utils.SpringBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author saml
 * @version 1.0
 * @date 2019-02
 */
@Configuration
@EnableSwagger2
public class WebMvcConfg extends WebMvcConfigurationSupport {


    /**
     * swagger2 SwaggerBootstrapUi 配置
     *
     * @see Swagger2
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        // 添加自定义的converter
        for (Converter<?, ?> converter : SpringBeanFactory.getBeansOfType(Converter.class).values()) {
            registry.addConverter(converter);
        }

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new PageableArgumentResolver());
        argumentResolvers.add(new DateArgumentResolver());
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        for (HttpMessageConverter<?> converter : converters) {
            System.err.println(converter);
        }
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteDateUseDateFormat);
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        // 处理中文乱码问题
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        stringConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(stringConverter);
        converters.add(fastConverter);
    }
}
