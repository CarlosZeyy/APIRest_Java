package br.com.carlosmoises.apirestusers.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI customOpenApi() {
      return new OpenAPI()
              .info(new Info()
                      .title("API de Gerenciamento de usuários")
                      .version("2.0")
                      .description("Esta API permite o CRUD completo de usuários, incluindo criptografica de senhas e validações de e-mail único.")
                      .license(new License().name("Apache 2.0").url("http://springdoc.org"))
              );
  }
}
