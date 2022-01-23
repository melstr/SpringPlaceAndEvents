package ru.meleshkin.placesandevents.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * @author Meleshkin Alexandr
 * @since 23.01.2022
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "PlacesAndEvents",
        version = "1.0.0",
        description = "Service allows to operate with organizations and events they have created."))
public class SwaggerConfiguration {
}
