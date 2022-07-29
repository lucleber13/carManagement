package com.cbcode.car_management.security;

import com.cbcode.car_management.model.CustomUserBean;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwttoken.secret}")
    private String jwtTokenSecret;
    @Value("${jwttoken.expiration}")
    private long jwtTokenExpiration;

    public String generateJWTToken(Authentication authentication) {
        CustomUserBean userPricipal = (CustomUserBean) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPricipal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenExpiration))
                .signWith(SignatureAlgorithm.ES512, jwtTokenSecret)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtTokenSecret)
                    .parseClaimsJws(token);

        } catch (UnsupportedJwtException exp) {
            System.out.println("claimsJws argument does not represent Claims JWS " + exp.getMessage());
        } catch (MalformedJwtException exp) {
            System.out.println("claimsJws string is not a valid JWS " + exp.getMessage());
        } catch (SignatureException exp) {
            System.out.println("claimsJws JWS signature validation failed " + exp.getMessage());
        } catch (ExpiredJwtException exp) {
            System.out.println("Claims has an expiration time before the method is invoked " + exp.getMessage());
        }catch (IllegalArgumentException exp) {
            System.out.println("claimsJws string is null or empty or only whitespace " + exp.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtTokenSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
