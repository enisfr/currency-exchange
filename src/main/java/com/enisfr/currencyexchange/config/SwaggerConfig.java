package com.enisfr.currencyexchange.config;

import java.util.function.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String TITLE = "Currency Exchange App";
  private static final String DESC = "This Swagger UI environment is generated for the " + TITLE;
  private static final String ERROR = "/error.*";
  private static final String LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.html";
  private static final String LICENSE = "Apache 2.0";
  private static final String VERSION = "1.0";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .paths(Predicate.not(PathSelectors.regex(ERROR)))
        .build()
        .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder()
        .title(TITLE)
        .description(DESC)
        .license(LICENSE)
        .licenseUrl(LICENSE_URL)
        .version(VERSION)
        .build();
  }

}
