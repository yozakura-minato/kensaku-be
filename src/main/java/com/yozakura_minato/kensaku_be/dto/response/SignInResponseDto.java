package com.yozakura_minato.kensaku_be.dto.response;

import lombok.*;

/**
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code accessToken} - (String)</li>
 *     <li>{@code refreshToken} - (String)</li>
 * </ul>
 */
@Getter
@Setter
@Builder
public class SignInResponseDto {

    private String accessToken;
    private String refreshToken;

}
