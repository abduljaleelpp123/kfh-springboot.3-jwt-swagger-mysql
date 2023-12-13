package com.abdul.kfh;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** http://localhost:8080/swagger-ui/index.html
 * Reference Links :
 * <a href="https://springdoc.org/faq.html#how-can-i-configure-swagger-ui">Customize Swagger UI</a>
 * <a href="https://stackoverflow.com/questions/59291371/migrating-from-springfox-swagger-2-to-springdoc-open-api">StackOverflow Link</a>
 */

@Configuration
public class OpenApiConfig {

	
	
	
	
    @Bean
    OpenAPI awesomeAPI() {
        return new OpenAPI()
                .info(new Info().title("Abdul jaleel->KFH->Java Technical Assessment")
                        .description("Spring boot->JWT-REST->API")
                        .version("1.0")
                        
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Abdul Jaleel, abduljaleel123@gmail.com")
                        .url("linkedin.com/in/abduljaleelpp/"));
    }

}


