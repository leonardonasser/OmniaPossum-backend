package br.com.omniaPossum.domain.auth;

import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import br.com.omniaPossum.domain.usuario.Usuario;
import br.com.omniaPossum.infra.exceptions.ValidationException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import java.time.Duration;
import java.time.Instant;

@ApplicationScoped
public class TokenIssuer {

    private static final Duration LOGIN_TOKEN_DURATION = Duration.ofDays(30);
    private static final Duration RESET_PASSWORD_TOKEN_DURATION = Duration.ofHours(1);

    @Inject
    JWTParser jwtParser;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    public String issueForLogin(Usuario usuario) {
        JsonObject claims = Json.createObjectBuilder()
            .add(Claims.groups.name(), usuario.getRole().name())
            .build();

        return generateToken(usuario.getEmail(), LOGIN_TOKEN_DURATION, claims);
    }

    public String issueForPasswordReset(Usuario usuario) {
        return generateToken(usuario.getEmail(), RESET_PASSWORD_TOKEN_DURATION);
    }

    public String validateAndGetSubjectEmail(String token) {
        try {
            JsonWebToken jwtToken = jwtParser.parse(token);
            return jwtToken.getSubject();
        } catch (ParseException e) {
            throw new ValidationException("Token inválido", e);
        }
    }

    private String generateToken(String subject, Duration duration) {
        return baseJwt(subject)
            .expiresAt(Instant.now().plus(duration))
            .sign();
    }

    private String generateToken(String subject, Duration duration, JsonObject claims) {
        return baseJwt(subject, claims)
            .expiresAt(Instant.now().plus(duration))
            .sign();
    }

    private String generateToken(String subject, JsonObject claims) {
        return baseJwt(subject, claims).sign();
    }

    private JwtClaimsBuilder baseJwt(String subject) {
        JsonObject claims = Json.createObjectBuilder().build();
        return baseJwt(subject, claims);
    }

    private JwtClaimsBuilder baseJwt(String subject, JsonObject claims) {
        return Jwt
            .claims(claims)
            .issuer(issuer)
            .subject(subject)
            .upn(subject)
            .issuedAt(Instant.now());
    }
}
