package com.yozakura_minato.kensaku_be.dto.request;

import com.yozakura_minato.kensaku_be.annotation.isPasswordStrong.IsStrongPassword;
import com.yozakura_minato.kensaku_be.annotation.normalizedEmail.NormalizedEmail;
import com.yozakura_minato.kensaku_be.annotation.normalizedString.NormalizedString;
import com.yozakura_minato.kensaku_be.exception.message.SignUpException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * <p>Attributes</p>
 * <ul>
 *     <li>{@code email} - (String)</li>
 *     <li>{@code displayName} - (String) not unique</li>
 *     <li>{@code password} - (String) plain password</i>
 * </ul>
 */
public record SignUpRequestDto (

    @NormalizedEmail
    @NotBlank(message = SignUpException.Email.nulls)
    @Email(message = SignUpException.Email.format)
    String email,

    @NormalizedString
    @NotBlank(message = SignUpException.DisplayName.nulls)
    @Size(min = 3, max = 30, message = SignUpException.DisplayName.length)
    @Pattern(regexp = "^[A-za-z0-9 ]+$", message = SignUpException.DisplayName.format)
    String displayName,

    @NormalizedString
    @NotBlank(message = SignUpException.Password.nulls)
    @Size(min = 8, max = 50, message = SignUpException.Password.length)
    @IsStrongPassword
    String password

) {};
