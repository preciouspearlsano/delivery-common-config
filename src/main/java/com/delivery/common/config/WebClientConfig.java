package com.delivery.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean(name = "gatewayWebClient")
    public WebClient webClient() {
        return WebClient.builder()
                .clientConnector(clientHttpConnector())
                .build();
    }

    private ClientHttpConnector clientHttpConnector() {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(60000)) 
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000); 
        return new ReactorClientHttpConnector(httpClient);
    }
}