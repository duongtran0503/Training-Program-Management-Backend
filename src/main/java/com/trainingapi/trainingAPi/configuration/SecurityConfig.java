package com.trainingapi.trainingAPi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINTS_POST = {
            "/auth/login", "/auth/introspect"
    };
    private  final  String[]  PUBLIC_ENDPOINTS_GET = {"/auth/hello"};

    @Value("${jwt.signerKey}")
    private  String signerKey;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
       httpSecurity.authorizeHttpRequests(request->
                request.requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS_POST).permitAll()
                        .requestMatchers(HttpMethod.GET,PUBLIC_ENDPOINTS_GET).permitAll()
                        .anyRequest()
                        .authenticated()
               );

       httpSecurity.oauth2ResourceServer(oauth2->oauth2.jwt((jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
       return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
