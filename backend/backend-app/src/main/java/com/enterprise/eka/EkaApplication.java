package com.enterprise.eka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.enterprise.eka")
@EntityScan(basePackages = "com.enterprise.eka.service.entity")
@EnableJpaRepositories(basePackages = "com.enterprise.eka.service.repository")
@EnableCaching
@EnableAsync
@EnableScheduling
public class EkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EkaApplication.class, args);
    }
}
