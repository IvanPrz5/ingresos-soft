package com.ingresos_soft.Auth.Utils.Token;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

    @Setter
    private static UserDetailsService userDetailsService;

    private final static String ACCESS_TOKEN_SECRET = "4qhq8LrEBfycaRHxhdb9zURb2rf8e7Ud";
    private final static Long ACCESS_TOKEN_VALIDITY_ACCESS = 2_592_000L;

    public static String createToken(String nombre, String email) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_ACCESS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            if (email == null) {
                return null;
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } catch (JwtException e) {
            return null;
        }
    }
}