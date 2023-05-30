package io.todimu.practice.studentportal.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.todimu.practice.studentportal.utils.AuthoritiesConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class TokenProvider {

    public JwtToken createToken(Authentication authentication) {
        JwtToken token = null;
        if (authentication != null) {
            SecretKey secretKey = Keys.hmacShaKeyFor(AuthoritiesConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwtToken = Jwts.builder().setIssuer("student-app").setSubject("jwt-token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + 30000000))
                    .signWith(secretKey).
                    compact();

            token = new JwtToken(jwtToken);
        }

        return token;
    }

    public boolean validateToken(String jwtToken) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(AuthoritiesConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder().setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
            return true;
        } catch (RuntimeException exception) {
            System.err.println("token validation failed");
            return false;
        }
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}

