package com.sang.sangschoolback.security;

import com.sang.sangschoolback.entity.Personnel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.lang.System.currentTimeMillis;

@Component
public class JwtTokenUtils implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;



    @Value("${jwt.secret}")
    private String secret;

    /**
     * Retourne le nom de l'utilisateur compris dans le Token JWT.
     *
     * @param token Le Token JWT.
     * @return le nom de l'utilisateur.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Retourne la date d'expiration du Token JWT.
     *
     * @param token Le Token JWT.
     * @return La date d'expiration du Token JWT.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * retour l'auteur du token JWT
     * @param token
     * @param claimsResolver
     * @return
     * @param <T>
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Retourne les les toutes informations du Token JWT.
     *
     * @param token Le Token JWT.
     * @return le nom de l'utilisateur.
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Vérifie si le Token JWT a expiré.
     *
     * @param token Le Token JWT.
     * @return true si le token a expiré.
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Génère le Token JWT de l'utilisateurs rensigné.
     *
     * @param utilisateur l'utilisateur.
     * @return le Token JWT.
     */
    public String generateToken(Personnel utilisateur) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", utilisateur.getId());
        claims.put("nom", utilisateur.getNom());
        claims.put("prenoms", utilisateur.getPrenoms());
        claims.put("username", utilisateur.getUsername());
        claims.put("role", utilisateur.getRole().name());
        claims.put("statut", utilisateur.getStatut());
        return doGenerateToken(claims, utilisateur.getUsername());
    }

    /**
     * Permet de comptacter les attituts de l'utilisateur pour la génétaion du Token JWT.
     *
     * @param claims Les attributs.
     * @param subject le username de l'utilisateur.
     * @return Les attributs formatés.
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(currentTimeMillis()))
                .setExpiration(new Date(currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(HS512, secret).compact();
    }

    /**
     * Permet de valider la correspondace entre les informations du Token JWT et celles d'un utilisateur.
     *
     * @param token Le Token JWT.
     * @param userDetails L'utilisateur.
     * @return true si les informations corresponde et le Token JWT n'a pas expiré.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

