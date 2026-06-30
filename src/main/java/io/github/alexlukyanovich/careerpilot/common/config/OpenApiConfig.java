package io.github.alexlukyanovich.careerpilot.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI careerPilotOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CareerPilot AI API")
                        .description("AI assistant for job search, vacancy analysis and cover letter generation")
                        .version("0.1.0")
                );
    }
}
