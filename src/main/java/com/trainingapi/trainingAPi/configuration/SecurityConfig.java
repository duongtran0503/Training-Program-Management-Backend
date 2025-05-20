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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINTS_POST = {
            "/api/auth/login", "/api/auth/introspect",
            "/api/users/lecturer", "/api/users/lecturer-account",
            "/api/courses", "/api/courses/course-syllabus/{id}", "/api/courses/prerequisites/{courseCode}"
    };
    private final String[] PUBLIC_ENDPOINTS_GET = {
            "/api/auth/hello",
            "/api/users/lecturer/get-all",
            "/api/users/lecturer/{id}",
            "/api/users/lecturer/search",
            "/api/users/list-lecturers",
            "/api/users/search-lecturer",
            "/api/courses",
            "/api/courses/{courseCode}",
            "/api/courses/search",
            "/api/courses/course-syllabus",
            "/api/courses/course-syllabus/{id}",
            "/api/courses/course-syllabus/search"
    };
    private final String[] PUBLIC_ENDPOINTS_PUT = {
            "/api/users/lecturer/{id}",
            "/api/users/update-lecturer/{id}",
            "/api/courses/{courseCode}",
            "/api/courses/course-syllabus/{id}"
    };
    private final String[] PUBLIC_ENDPOINTS_DELETE = {
            "/api/users/lecturer/{id}",
            "/api/users/delete-lecturer/{id}",
            "/api/courses/{courseCode}",
            "/api/courses/course-syllabus/{id}"
    };

    @Value("${jwt.signerKey}")
    private  String signerKey;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
       httpSecurity.authorizeHttpRequests(request->
                request.requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS_POST).permitAll()
                        .requestMatchers(HttpMethod.GET,PUBLIC_ENDPOINTS_GET).permitAll()
                        .requestMatchers(HttpMethod.PUT,PUBLIC_ENDPOINTS_PUT).permitAll()
                        .requestMatchers(HttpMethod.DELETE,PUBLIC_ENDPOINTS_DELETE).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
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

    @Bean
   public  CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:3001")); 
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfiguration);
        return  new CorsFilter(source);
    }


}
