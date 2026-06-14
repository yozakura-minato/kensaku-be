package com.yozakura_minato.kensaku_be.dto.response;

import java.util.UUID;

/**
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code userId} - (int)</li>
 *     <li>{@code email} - (String)</li>
 *     <li>{@code displayName} - (String)</li>
 * </ul>
 */
public record SignUpResponseDto (

    UUID userId,
    String email,
    String displayName

) {};
