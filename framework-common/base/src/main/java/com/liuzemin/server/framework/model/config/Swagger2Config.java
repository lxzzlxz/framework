package com.liuzemin.server.framework.model.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

    @Autowired
    private Environment environment;

    @Bean
    public Docket createRestApi(){

        ApiInfo apiInfo = new ApiInfoBuilder().title("中铁物联平台后台接口api文档")
                .description("restful风格接口，服务名称："+environment.getProperty("application.description")+"</br>路由标识："+environment.getProperty("spring.application.name")).version("1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select().apis(basePackage
                ("com.liuzemin.server.*.controller")).paths(PathSelectors.any()).build();
    }


    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return new Function<Class<?>, Boolean>() {
            @Override
            public Boolean apply(Class<?> input) {
                return input.getPackage().getName().matches(basePackage);
            }
        };
    }

    public Predicate<RequestHandler> basePackage(final String basePackage) {
        return new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {

                return Swagger2Config.declaringClass(input).transform(Swagger2Config.handlerPackage(basePackage)).or
                        (true);
            }
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}
