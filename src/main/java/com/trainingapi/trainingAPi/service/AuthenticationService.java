package com.trainingapi.trainingAPi.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.trainingapi.trainingAPi.dto.request.LoginRequest;
import com.trainingapi.trainingAPi.dto.response.AuthenticationResponse;
import com.trainingapi.trainingAPi.entity.User;
import com.trainingapi.trainingAPi.exception.AppException;
import com.trainingapi.trainingAPi.exception.ErrorCode;
import com.trainingapi.trainingAPi.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
      UserRepository userRepository;
      PasswordEncoder passwordEncoder;

      @NonFinal
      @Value("${jwt.signerKey}")
      protected String SIGNER_KEY;

      public AuthenticationResponse login(LoginRequest request) {
           var user = userRepository.findByUsername(request.getUsername())
                   .orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
           boolean isAuthenticated = passwordEncoder.matches(request.getPassword(),user.getPassword());
           if(!isAuthenticated)
               throw  new AppException(ErrorCode.UNAUTHENTICATED);
           String token = generateToken(user);
           return  AuthenticationResponse.builder().token(token).build();
      }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("training.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(24, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", user.getRole())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }
}
