package br.com.aceleradev.java.centraldeerros.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

public class TokenService {
    private static final String SECRET = "2980d62e-923a-4e07-bb6f-7e476079b2fe";
    private static final long EXPIRATION_TIME = 86400000;
    private static final String TOKEN_TYPE = "Bearer ";

    public static String create(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    static boolean hasJwtToken(String token) {
        return (!StringUtils.isEmpty(token) && token.startsWith(TOKEN_TYPE));
    }

    static String validateToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_TYPE, "")).getBody()
                .getSubject();
    }
}
