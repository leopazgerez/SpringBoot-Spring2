package com.mindhub.todolist.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

// Es necesario que sea componente para que spring lo tenga en contexto.
// Para que?... Para que se pueda realizar la inyeccion de dependecias con el @AutoWired
@Component
public class JwtUtils {
    //La clave secreta que se va atulizar para firmar el JWT
    private final SecretKey secretKey;
    //Este anotador más el formato "${value}" le estamos diciendo a java que vaya a buscar ese string en el application.property
    // Estos datos sensibles deben ser obtenidos de variable de entornos.
    @Value("${jwt.expiration}")
    private long expiration;

    public JwtUtils(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    //  Genera el token
    public String generateToken(String username) {
        return Jwts.builder()// Usa "claim" para agregar otros campos
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    //    Verifica si el JWT es válido
    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    //  Verifica lo que está llegando en el JWT
    private Claims parseClaims(String token) {
        return Jwts.parser().verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Verifica si aún está vigente el JWT
    private boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }
}
