package com.enigma.PulsaProject.Configuration;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors
							.basePackage("com.enigma.PulsaProject.Controller"))
					.paths(PathSelectors.regex("/.*"))
					.build().apiInfo(apiEndPointsInfo());
		}

		private ApiInfo apiEndPointsInfo() {

			return new ApiInfoBuilder().title("Enigma REST API")
					.description("Enigma Ewallet API")
					.contact(new Contact("Septiariani Rahayu", "www.enigmacamp.com", "septiarianirahayu.cc@gmail.com"))
					.license("Apache 2.0")
					.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
					.version("1.0.0")
					.build();
		}
	}

