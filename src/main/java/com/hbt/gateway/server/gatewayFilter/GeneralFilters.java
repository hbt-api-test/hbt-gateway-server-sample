package com.hbt.gateway.server.gatewayFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class GeneralFilters implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Ejecutando pre filtro");
        exchange.getRequest().mutate().headers(h -> h.add("token", "12345"));

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("ejecutando filtro post");

            //If funcional
            Optional.ofNullable(exchange.getRequest()
                            .getHeaders()
                            .getFirst("token"))
                            .ifPresent(valor -> {
                                exchange.getResponse()
                                        .getHeaders()
                                        .add("token", valor);
                            });

            exchange.getResponse().getCookies().add("color",
                    ResponseCookie.from("color", "rojo").build());

        }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
