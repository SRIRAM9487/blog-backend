package com.spring.project.blog.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    public String secretKey = "";

    public JwtService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()  ))
                .expiration(new Date(System.currentTimeMillis()+ 60*60*60*60))
                .and()
                .signWith(getKey())
                .compact()
                ;

    }

    public SecretKey getKey(){
        byte[] byteKeys = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(byteKeys);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                ;
    }
    private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction){
       final Claims claims = extractAllClaims(token) ;
       return claimsTFunction.apply(claims);
    }


    public String extractUserName(String token) {
        return extractClaims(token,claims -> claims.getSubject());
    }

    public Date expirationDate(String token){
        return extractClaims(token,claims -> claims.getExpiration());
    }
    public boolean isTokenExpired(String token){
        return expirationDate(token).before(new Date());
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        boolean valid = (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
        return valid;
    }
}
