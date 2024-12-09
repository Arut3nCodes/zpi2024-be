package com.zpi.fryzland.security.jwt;

import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.service.CustomerService;
import com.zpi.fryzland.service.EmployeeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtTokenGenerator {
    CustomerService customerService;
    EmployeeService employeeService;
    private final String secretKey = System.getenv("JWT_SECRET");
    private final long tokenValidityTimeDuration = 1000 * 60 * 60; //one hour (milliseconds)

    public String generateToken(Authentication authentication){
        String subjectUsername = authentication.getName();
        String subjectRole = authentication.getAuthorities().iterator().next().toString();
        System.out.println(subjectRole);
        int id = subjectRole.equals("ROLE_USER_EMPLOYEE") ? employeeService.getByEmployeeEmail(subjectUsername).get().getEmployeeID() : customerService.findByEmail(subjectUsername).get().getCustomerID();
        Date currentTime = new Date(System.currentTimeMillis());
        Date tokenExpirationDate = new Date(currentTime.getTime() + tokenValidityTimeDuration);
        return Jwts.builder()
                .subject(subjectUsername)
                .claim("userId", id)
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

    public String getRoles(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload().get("role", String.class);
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
