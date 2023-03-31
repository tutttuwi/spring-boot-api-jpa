package com.example.system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition( // @see <https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md>
        info = @Info(
                title = "[TITLE] System Open API",
                version = "1.0.0", // Semantic Versioning
                description = "Description How to use System API",
                license = @License(name = "MIT", url = "https://example.com"), // decide your license and write your site url.
                contact = @Contact(url = "http://example-server.com", name = "[your name here]", email = "example@example.com")
        ),
// TODO: 使い方調査..
//        security = {
//                @SecurityRequirement(name = "req 1", scopes = {"a", "b"}),
//                @SecurityRequirement(name = "req 2", scopes = {"b", "c"})
//        },

// TODO: サーバの変数管理
//        servers = {
//                @Server(
//                        description = "server 1",
//                        url = "https://{domain}:{port}/{basePath}",
//                        variables = {
//                                @ServerVariable(name = "domain", description = "domain name", defaultValue = "example.com", allowableValues = {"example.com", "example2.com"}),
//                                @ServerVariable(name = "port", description = "port", defaultValue = "8080", allowableValues = {"8080", "8081"}),
//                                @ServerVariable(name = "basePath", description = "basePath", defaultValue = "system", allowableValues = {"system", "system2"})
//                        })
//        },
        servers = {
                @Server(
                        description = "Local Server",
                        url = "http://localhost:8080/system"
                ),
                @Server(
                        description = "Development Server",
                        url = "http://dev.local/system"
                ),
                @Server(
                        description = "Staging Server",
                        url = "http://stg.local/system"
                ),
                @Server(
                        description = "Production Server",
                        url = "http://prd.system.com/system"
                ),
        }
)
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
