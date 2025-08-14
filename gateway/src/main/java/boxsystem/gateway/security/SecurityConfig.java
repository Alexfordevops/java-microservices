package boxsystem.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/**", "/actuator/**").permitAll() // rotas públicas
                        .anyExchange().authenticated() // demais rotas precisam de autenticação
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable); // desabilita CSRF de forma não-deprecated

        return http.build();
    }
}

