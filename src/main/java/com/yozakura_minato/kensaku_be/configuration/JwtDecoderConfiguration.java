package com.yozakura_minato.kensaku_be.configuration;

import com.nimbusds.jose.JOSEException;
import com.yozakura_minato.kensaku_be.exception.message.AuthenticationException;
import com.yozakura_minato.kensaku_be.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Objects;

@Component
public class JwtDecoderConfiguration implements JwtDecoder {

    @Value("${security.jwt.encode.secret-key}")
    private String secretKey;

    @Autowired
    private JwtService jwtService;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    /**
     * <p>Validate JWT tokens</p>
     * <p>Using:</p>
     * <ul>
     *     <li>Secret key: {@code security.jwt.encode.secret-key}</ol>
     *     <li>Algorithm: {@code HS256}</ol>
     * </ul>
     * @param token (String) the JWT value
     * @return (Jwt) Validated JWT token
     * @throws AuthenticationException invalid token
     * @throws JwtException ParseException, JOSEException
     */
    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            if(!jwtService.verifyToken(token)) {
                throw new RuntimeException(AuthenticationException.invalidToken);
            }
            if(Objects.isNull(nimbusJwtDecoder)) {
                SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HS256");
                nimbusJwtDecoder = NimbusJwtDecoder
                        .withSecretKey(secretKeySpec)
                        .macAlgorithm(MacAlgorithm.HS256)
                        .build();
            }
        } catch (ParseException | JOSEException exception) {
            throw new RuntimeException(exception);
        }
        return nimbusJwtDecoder.decode(token);
    }

}
