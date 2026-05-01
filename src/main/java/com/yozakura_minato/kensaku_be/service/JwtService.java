package com.yozakura_minato.kensaku_be.service;

import com.nimbusds.jose.*;
import com.yozakura_minato.kensaku_be.dto.internal.JwtInformation;
import com.yozakura_minato.kensaku_be.dto.internal.TokenPayload;
import com.yozakura_minato.kensaku_be.entity.Users;

import java.text.ParseException;

public interface JwtService {

    /**
     * <p>Access token information:</p>
     * <ul>
     *     <li>Header: {@code HS256} algorithm</li>
     *     <li>Payload: email</li>
     *     <li>Signature: {@code security.jwt.encode.secret-key}</li>
     *     <br>
     *     <li>Issue time: current (Date)</li>
     *     <li>Epiration time: {@code security.jwt.access-token.validity-minutes} after</li>
     * </ul>
     *
     * @param user (Users)
     * @return (String)
     * @throws JOSEException
     */
    TokenPayload generateAccessToken(Users user);

    /**
     * <p>Refresh token information:</p>
     * <ul>
     *     <li>Header: {@code HS256} algorithm</li>
     *     <li>Payload: email</li>
     *     <li>Signature: {@code security.jwt.encode.secret-key}</li>
     *     <br>
     *     <li>Issue time: current (Date)</li>
     *     <li>Epiration time: {@code security.jwt.refresh-token.validity-days} after</li>
     * </ul>
     *
     * @param user (Users)
     * @return (String)
     * @throws JOSEException
     */
    TokenPayload generateRefreshToken(Users user);

    /**
     * Verify for token expiration time
     * @param token (String)
     * @return (boolean)
     */
    boolean verifyToken(String token) throws ParseException, JOSEException;

    JwtInformation parseToken(String token) throws ParseException;

}
