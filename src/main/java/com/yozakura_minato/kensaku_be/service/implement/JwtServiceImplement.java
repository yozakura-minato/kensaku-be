package com.yozakura_minato.kensaku_be.service.implement;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.yozakura_minato.kensaku_be.entity.Users;
import com.yozakura_minato.kensaku_be.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
public class JwtServiceImplement implements JwtService {

    @Value("${security.jwt.encode.secret-key}")
    private String secretKey;

    @Value("${security.jwt.access-token.validity-minutes}")
    private int accessTokenValidityMinutes;

    @Value("${security.jwt.refresh-token.validity-days}")
    private int refreshTokenValidityDays;

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateAccessToken(Users user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        Date issueTime = new Date();
        Date expirationTime = Date.from(issueTime.toInstant().plus(accessTokenValidityMinutes, ChronoUnit.MINUTES));

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet
                .Builder()
                .subject(user.getEmail())
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException exception) {
            throw new RuntimeException(exception);
        }

        return jwsObject.serialize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateRefreshToken(Users user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        Date issueTime = new Date();
        Date expirationTime = Date.from(issueTime.toInstant().plus(refreshTokenValidityDays, ChronoUnit.DAYS));

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet
                .Builder()
                .subject(user.getEmail())
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException exception) {
            throw new RuntimeException(exception);
        }
        return jwsObject.serialize();
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
        return signedJWT.verify(new MACVerifier(secretKey));
    }

}
