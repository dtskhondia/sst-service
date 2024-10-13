package ge.bog.sst_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(
            new Info()
                .title("SST Service API")
                .description("API To Manage Payments, Providers and Terminals")
                .version("1.0.0")
        );
    }
}
