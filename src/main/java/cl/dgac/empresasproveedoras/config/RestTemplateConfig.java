package cl.dgac.empresasproveedoras.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // Eureka se encarga de resolver "http://DGAC-MS-SEGUROS" automáticamente
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}