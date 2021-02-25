package training.spa.api.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(paths())
				.build()
				.apiInfo(apiInfo());
	}

	private Predicate<String> paths() {
		// ドキュメント生成対象URLを指定
		return Predicates.or(Predicates.containsPattern("/article"), Predicates.containsPattern("reply"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("Java spa開発トレーニング（API編）" //title
				, "掲示板用のAPIです" //descroption
				, "V1" //version
				, "" //termsOfServiceUrl
				, new Contact(
						"Tommy" //name
						, null //URL
						, "satoshi.tomiyama@gmail.com" //e-mail
				)
				, "Copyright 2021 All rights reserved." //license
				, "" //licenseUrl
				, new ArrayList<VendorExtension>()); //VendorException
	}
}
