package com.arilsongomes.iotapi.security.jwt;


import com.arilsongomes.iotapi.entity.User;
import com.arilsongomes.iotapi.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {

    private final UserRepository userRepository;

    @Value("${spring.jwt.secret}")
    private String JWT_SECRET;

    @Value("${spring.jwt.jwtExpirationtime}")
    private int JWT_EXPIRATION_TIME;


    public String getGenaretedToken(String userName) {
        Map<String, Object> claims = new HashMap<>();

        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + userName));

        
        claims.put("userId", user.getId());

        return generateTokenForUser(claims, userName);
    }

    private String generateTokenForUser(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_TIME))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignedKey() {
        byte [] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserNameFromToken(String theToken){
        return extractClaim(theToken, Claims::getSubject);
    }

    public Long extractUserIdFromToken(String theToken){
        return extractClaim(theToken, claims -> claims.get("userId", Long.class));
    }

    public Date extractExpirationTimeFromToken(String theToken){
        return extractClaim(theToken, Claims::getExpiration);
    }

    public Boolean validateToken(String theToken, UserDetails userDetails){
        final String userName = extractUserNameFromToken(theToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(theToken));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String theToken){
        return extractExpirationTimeFromToken(theToken).before(new Date());
    }
}
