package com.zpi.fryzland.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenGenerator {
    private final String secretKey = System.getenv("JWT_SECRET");
    private final long tokenValidityTimeDuration = 1000 * 60 * 60; //one hour (milliseconds)

    public String generateToken(Authentication authentication){
        String subjectUsername = authentication.getName();
        String subjectRole = authentication.getAuthorities().iterator().next().toString();
        Date currentTime = new Date(System.currentTimeMillis());
        Date tokenExpirationDate = new Date(currentTime.getTime() + tokenValidityTimeDuration);
        return Jwts.builder()
                .subject(subjectUsername)
                .claim("role", subjectRole)
                .issuedAt(currentTime)
                .expiration(tokenExpirationDate)
                .signWith(key())
                .compact();
    }

    Key key(){
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public List<String> getRoles(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload().get("roles", List.class);
    }

    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;

    }

    //todo: Samodzielne generowanie klucza w trakcie działania programu

}
