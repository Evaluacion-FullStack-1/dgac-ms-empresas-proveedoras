package cl.dgac.empresasproveedoras.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${seguros.base-url:http://dgac-ms-seguros}")
    private String segurosBaseUrl;

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClientSeguros(WebClient.Builder builder) {
        return builder.baseUrl(segurosBaseUrl).build();
    }
}