package com.zerobase.school.user.config;

import com.zerobase.school.user.domain.UserType;
import com.zerobase.school.user.dto.UserResponse;
import com.zerobase.school.user.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtAuthenticationProvider {
    private final String secretKey = "secretKey";

    private final long tokenValidTime = 1000L * 60 * 60 * 24;

    public String createToken(String userPk, Long id, UserType userType){
        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPk)).setId(id.toString());
        claims.put("roles", userType);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String jwtToken){

        try{
            Jws<Claims> claimsJwts = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claimsJwts.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    public UserResponse getUserResponse(String token){
        Claims c = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return new UserResponse(Long.valueOf(Aes256Util.decrypt(c.getId())),Aes256Util.decrypt(c.getSubject()));
    }


}
