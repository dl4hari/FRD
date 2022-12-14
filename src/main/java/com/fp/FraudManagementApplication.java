package com.fp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.fp.repository")
public class FraudManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudManagementApplication.class, args);
    }

}
