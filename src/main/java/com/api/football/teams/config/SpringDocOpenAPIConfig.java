package com.api.football.teams.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@OpenAPIDefinition(
        info = @Info(
                title = "Teams API"
                , description = "api to obtain data from football clubs"
                , version = "1"
                , contact = @Contact(name = "João Paulo", email = "jp.sport17@gmail.com")
                , license = @License(

                )
                , termsOfService = "Terms of service"
        ),
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
public class SpringDocOpenAPIConfig {

}
