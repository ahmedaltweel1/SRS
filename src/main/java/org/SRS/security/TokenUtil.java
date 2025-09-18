package org.SRS.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil  {

    private final String CLAIMS_SUBJECT="sub";
    private final String CLAIMS_CREATED="created";


    @Value("${auth.secret}")
        private String SECRET_KEY;

    @Value("${auth.expiration}")
    private Long  TOKEN_VALIDITY    = 604800L;


    public String generateToken(UserDetailsImpl user){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIMS_SUBJECT,user.getUsername());
        claims.put(CLAIMS_CREATED,new Date());

        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }



    public String getUsernameFromToken(String Token){
        try{
            Claims claims=getClaims(Token);
            return claims.getSubject();

        }catch(Exception ex){
            return null;
        }
    }

    public Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+TOKEN_VALIDITY*1000);
    }

    public boolean isTokenValid(String token,UserDetailsImpl userDetails){
        String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenValidExpired(token));
    }

    private boolean isTokenValidExpired(String token){
        Date expriation=getClaims(token).getExpiration();
        return expriation.before(new Date());
    }

    private Claims getClaims(String token){
    Claims claims;

    try{
        claims=Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody();
    }catch(Exception ex){
        claims=null;
    }
    return claims;
    }

}
