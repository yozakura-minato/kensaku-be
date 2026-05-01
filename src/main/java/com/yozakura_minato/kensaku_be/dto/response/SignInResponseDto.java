package com.yozakura_minato.kensaku_be.dto.response;

import lombok.Builder;

/**
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code accessToken} - (String)</li>
 *     <li>{@code refreshToken} - (String)</li>
 * </ul>
 */
@Builder
public record SignInResponseDto (

    String accessToken,
    String refreshToken

) {};
