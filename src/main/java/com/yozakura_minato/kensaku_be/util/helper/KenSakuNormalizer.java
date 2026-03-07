package com.yozakura_minato.kensaku_be.util.helper;

import com.yozakura_minato.kensaku_be.dto.request.SignInRequestDto;
import com.yozakura_minato.kensaku_be.dto.request.SignUpRequestDto;

/**
 * <p>Helper function to normalize Request DTOs.</p>
 * <p>For examples: string strimming, ...</p>
 */
public class KenSakuNormalizer {

    /**
     * <p>Target attributes: {@code password} - trim string</p>
     * @param signInReq (SignInRequestDto)
     */
    public static void normalize(SignInRequestDto signInReq) {
        signInReq.setPassword(
                signInReq.getPassword().trim()
        );
    }

    /**
     * <p>Target attributes:</p>
     * <ul>
     *     <li>{@code password} - trim string</li>
     *     <li>{@code displayName} - trim string</li>
     * </ul>
     * @param signUpReq (SignUpRequestDto)
     */
    public static void normalize(SignUpRequestDto signUpReq) {
        signUpReq.setPassword(
                signUpReq.getPassword().trim()
        );
        signUpReq.setDisplayName(
                signUpReq.getDisplayName().trim()
        );
    }

}
