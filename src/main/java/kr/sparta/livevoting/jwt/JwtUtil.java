package kr.sparta.livevoting.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final SecretKey key;
    private final long accessExpiration;
    private final long refreshExpiration;

    public JwtUtil(@Value("${jwt.secret.key}") String secretKey,
                   @Value("${jwt.token-validity-in-seconds}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.accessExpiration = expiration * 1000; //밀리초 변환 - 1시간
        this.refreshExpiration = expiration * 24 * 7 * 1000; //밀리초 변환 - 7일
    }

    public String createJwtToken(Authentication authentication, long expiration) {
        //권한 얻기
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        //만료기한
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        //사용자 정보 꺼내기

        return Jwts.builder()
                .subject(authentication.getName())
                .claim("auth", roles)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(expirationDate)
                .compact();
    }

    public String createAccessToken(Authentication authentication) {
        return createJwtToken(authentication, accessExpiration);
    }

    public String createRefreshToken(Authentication authentication) {
        return createJwtToken(authentication, refreshExpiration);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth", String.class).split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new org.springframework.security.core.userdetails.User(
                claims.getSubject(),
                "",
                authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("만료된 Token: " + e.getMessage());
            return false;
        } catch (JwtException e) {
            System.out.println("유효하지 않은 Token: " + e.getMessage());
            return false;
        }
    }

    public String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Long getUserId(String token) {
        String strUserId = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();

        return Long.parseLong(strUserId);
    }
}