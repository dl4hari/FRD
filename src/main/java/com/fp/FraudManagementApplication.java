package com.fp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.fp.repository")
@OpenAPIDefinition(info = @Info(title = "Fraud Master API",
        version = "3.0", description = "Fraud Master API Information"))
public class FraudManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudManagementApplication.class, args);
    }

}
