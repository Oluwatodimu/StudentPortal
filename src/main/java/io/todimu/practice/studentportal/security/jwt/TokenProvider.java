package io.todimu.practice.studentportal.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.todimu.practice.studentportal.utils.AuthoritiesConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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
                    .setExpiration(new Date((new Date()).getTime() + 4000000))
                    .signWith(secretKey).
                    compact();

            token = new JwtToken(jwtToken);
        }

        return token;
    }

    public boolean validateToken(String jwtToken) {
        try {
            Claims claims = getClaimsFromToken(jwtToken);
            return true;
        } catch (RuntimeException exception) {
            System.err.println("token validation failed");
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaimsFromToken(token);
        String username = (String) claims.get("username");
        String authoritiesString = (String) claims.get("authorities");

        return new UsernamePasswordAuthenticationToken(username,
                null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesString));
    }

    private Claims getClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(AuthoritiesConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder().setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}

