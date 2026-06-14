package com.yozakura_minato.kensaku_be.dto.request;

import com.yozakura_minato.kensaku_be.annotation.normalizedEmail.NormalizedEmail;
import com.yozakura_minato.kensaku_be.annotation.normalizedString.NormalizedString;
import com.yozakura_minato.kensaku_be.exception.message.SignInException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code email} - (String)</li>
 *     <li>{@code password} - (String) plain password</li>
 * </ul>
 */
public record SignInRequestDto (

    @NormalizedEmail
    @NotBlank(message = SignInException.Email.nulls)
    @Email(message = SignInException.Email.format)
    String email,

    @NormalizedString
    @NotBlank(message = SignInException.Password.nulls)
    String password

) {};
