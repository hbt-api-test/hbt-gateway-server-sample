package com.hbt.gateway.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GatewayController {

    @GetMapping("/")
    public String index(Principal principal){
        return principal.getName();
    }
}
