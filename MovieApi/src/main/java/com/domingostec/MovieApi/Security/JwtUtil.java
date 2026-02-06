package com.domingostec.MovieApi.Security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = Jwts.parserBuilder()
                            .setSigningKey(SECRET_KEY)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
                            
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims :: getSubject);

    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims :: getExpiration);
    }

    public boolean isTokenValid(String token, String username){
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); 
    }

}
