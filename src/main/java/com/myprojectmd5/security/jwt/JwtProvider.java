package com.myprojectmd5.security.jwt;

import com.myprojectmd5.security.userprincipal.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "jwt@jwt";
    private int jwtExpiration = 86400;

    //ham create Token khi login
    public String createToken (Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 10000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    //ham goi trong class JwtTokenFilter
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException e){
                logger.error("Unsupported JWT Token -> Message:{}", e);
            } catch (MalformedJwtException e) {
            logger.error("Invalid format Token -> Message:{}", e);
        } catch (SignatureException e){
            logger.error("Invalid JWT signature -> Message:{}", e);
        }  catch (IllegalArgumentException e){
            logger.error("Illegal JWT Token -> Message:{}", e);
        }
        return false;
    }

    //ham goi trong class JwtTokenFilter
    public String getUserNameFromToken(String token){
        String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }
}
