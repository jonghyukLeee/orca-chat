package com.orca.chat.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("ORCA FC - Chat Service")
                    .description("ORCA FC Chat 서버 OpenAPI 문서")
                    .version("1.0.0")
            )
    }
}
