package com.hbt.gateway.server.gatewayFilter.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class HbtGatewayFilterFactory
        extends AbstractGatewayFilterFactory<HbtGatewayFilterFactory.Configuration> {

    public HbtGatewayFilterFactory() {
        super(Configuration.class);
    }

    @Override
    public GatewayFilter apply(Configuration config) {

        log.info("Ejecutando el gateway pre filter factory");
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Optional.ofNullable(config.cokieValue).ifPresent(cokie -> {
                    exchange.getResponse().addCookie(
                            ResponseCookie.from(
                                    config.cokieName, cokie).build()
                    );
                });


                log.info("Ejecutando el gateway post filter factory: " + config.message);
            }));
        };
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Configuration {

        private String message;
        private String cokieValue;
        private String cokieName;

    }
}
