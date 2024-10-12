package com.supervision.supDispatch.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/", "/index.html", "/manifest.json", "/favicon.ico", "/static/**", "/api/monitoring/**","/api/alerts/**").permitAll() // Permit all for these paths
                        .anyExchange().authenticated()
                )
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // Disable httpBasic
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable); // Disable formLogin

        return http.build();
    }
}





