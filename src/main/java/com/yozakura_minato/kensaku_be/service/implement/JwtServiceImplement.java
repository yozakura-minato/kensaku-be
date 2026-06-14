package com.yozakura_minato.kensaku_be.service.implement;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.yozakura_minato.kensaku_be.dto.internal.JwtInformation;
import com.yozakura_minato.kensaku_be.dto.internal.TokenPayload;
import com.yozakura_minato.kensaku_be.entity.RedisToken;
import com.yozakura_minato.kensaku_be.entity.Users;
import com.yozakura_minato.kensaku_be.exception.message.AuthenticationException;
import com.yozakura_minato.kensaku_be.repository.RedisTokenRepository;
import com.yozakura_minato.kensaku_be.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class JwtServiceImplement implements JwtService {

    @Value("${security.jwt.encode.secret-key}")
    private String secretKey;

    @Value("${security.jwt.access-token.validity-minutes}")
    private int accessTokenValidityMinutes;

    @Value("${security.jwt.refresh-token.validity-days}")
    private int refreshTokenValidityDays;

    @Autowired
    private RedisTokenRepository redisTokenRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TokenPayload generateAccessToken(Users user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        Date issueTime = new Date();
        Date expirationTime = Date.from(issueTime.toInstant().plus(accessTokenValidityMinutes, ChronoUnit.MINUTES));
        String jwtId = UUID.randomUUID().toString();

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet
                .Builder()
                .subject(user.getEmail())
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .jwtID(jwtId)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException exception) {
            throw new RuntimeException(exception);
        }

        String token = jwsObject.serialize();
        return TokenPayload.builder()
                .jwtId(jwtId)
                .token(token)
                .expirationTime(expirationTime)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TokenPayload generateRefreshToken(Users user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        Date issueTime = new Date();
        Date expirationTime = Date.from(issueTime.toInstant().plus(refreshTokenValidityDays, ChronoUnit.DAYS));
        String jwtId = UUID.randomUUID().toString();

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet
                .Builder()
                .subject(user.getEmail())
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .jwtID(jwtId)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException exception) {
            throw new RuntimeException(exception);
        }

        String token = jwsObject.serialize();
        return TokenPayload.builder()
                .jwtId(jwtId)
                .token(token)
                .expirationTime(expirationTime)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifyToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if(expirationTime.before(new Date())) {
            return false;
        }
        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Optional<RedisToken> existsJwtId = redisTokenRepository.findById(jwtId);
        if(existsJwtId.isPresent()) {
            throw new RuntimeException(AuthenticationException.invalidToken);
        }
        return signedJWT.verify(new MACVerifier(secretKey));
    }

    @Override
    public JwtInformation parseToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date issueTime = signedJWT.getJWTClaimsSet().getIssueTime();
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        return JwtInformation.builder()
                .jwtId(jwtId)
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .build();
    }

}
