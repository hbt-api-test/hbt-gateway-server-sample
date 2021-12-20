package com.hbt.gateway.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/clienteFallback")
    public Mono<String> clienteServiceFallback(){
        return Mono.just("El servicio cliente esta tomando mucho tiempo en responder o esta caido, por favor intentelo mas tarde");
    }

    @RequestMapping("/uploadFallback")
    public Mono<String> uploadServiceFallback(){
        return Mono.just("El servicio de cargas esta tomando mucho tiempo en responder o esta caido, por favor intentelo mas tarde");
    }

    @RequestMapping("/seguridadFallback")
    public Mono<String> seguridadServiceFallback(){
        return Mono.just("El servicio de seguridad esta tomando mucho tiempo en responder o esta caido, por favor intentelo mas tarde");
    }

}
