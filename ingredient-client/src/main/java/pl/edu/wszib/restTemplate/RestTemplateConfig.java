package pl.edu.wszib.restTemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Conditional(NotFeignAndNotWebClientCondition.class)
@Slf4j
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public CommandLineRunner startUp() {
        return args -> {
            log.info("*****************************************");
            log.info("    Configuration with WebClient         ");
            log.info("*****************************************");
        };
    }
}