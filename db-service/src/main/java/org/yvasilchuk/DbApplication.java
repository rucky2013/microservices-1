package org.yvasilchuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.yvasilchuk.security.IpAuthenticationProvider;

@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
@EnableDiscoveryClient
public class DbApplication {
    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class, args);
    }

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder() {
        return new ShaPasswordEncoder();
    }

    @Bean
    public IpAuthenticationProvider getAuthenticationProvider() {
        return new IpAuthenticationProvider();
    }
}
