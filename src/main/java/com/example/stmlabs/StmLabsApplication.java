package com.example.stmlabs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition
@EnableCaching
public class StmLabsApplication {

  public static void main(String[] args) {
    SpringApplication.run(StmLabsApplication.class, args);
  }

}
