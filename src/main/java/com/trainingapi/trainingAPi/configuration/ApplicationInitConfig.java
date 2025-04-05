package com.trainingapi.trainingAPi.configuration;

import com.trainingapi.trainingAPi.entity.User;
import com.trainingapi.trainingAPi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
      return  args -> {
          if(!userRepository.existsByUsername("admin")) {
              User user = User.builder().username("admin").password(passwordEncoder.encode("123")).role("admin").build();
              userRepository.save(user);
              log.warn("create  account admin success!");
          }
      };

    }
}
