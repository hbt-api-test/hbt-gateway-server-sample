package com.hbt.gateway.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebFluxSecurity
public class SpringSecurityConfig {

    @Autowired
    JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http){
        return http.authorizeExchange()
                .pathMatchers("/hbt/seguridad/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/hbt/cliente/**", "").permitAll()
                //.pathMatchers(HttpMethod.GET, "/hbt/cliente/page/**").hasAnyRole("ROL_ADMIN", "USER")
                //.pathMatchers("/hbt/clientes/create", "/hbt/clientes/edit/{id}", "/hbt/upload/file").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and()
                .cors().configurationSource(configurationSource())
                .and()
                .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable().build();
    }


    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsWebFilter> corsFilter(){
        FilterRegistrationBean<CorsWebFilter> bean = new FilterRegistrationBean<CorsWebFilter>(new CorsWebFilter(configurationSource()));
    }


}
