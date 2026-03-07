package com.yozakura_minato.kensaku_be.dto.response;

import lombok.*;

/**
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code userId} - (int)</li>
 *     <li>{@code email} - (String)</li>
 *     <li>{@code displayName} - (String)</li>
 * </ul>
 */
@Getter
@Setter
public class SignUpResponseDto {

    private int userId;
    private String email;
    private String displayName;

}
