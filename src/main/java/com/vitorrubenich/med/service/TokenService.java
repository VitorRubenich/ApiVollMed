package com.vitorrubenich.med.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vitorrubenich.med.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    protected String secret;


    public String createToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ApiVr.Med")
                    .withSubject(user.getLogin())
                    .withExpiresAt(dateExpires())
                    .sign(algorithm);
        }catch(Exception ex){
            throw new RuntimeException("Erro ao gerar token JWT",ex);
        }
    }

    public String getSubject(String tokenJWT){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ApiVr.Med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        }catch(JWTVerificationException exception){
            //invalid Exception
            throw new RuntimeException("Token JWT invalido ou expirado");
        }
    }
    private Instant dateExpires(){
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }
}
