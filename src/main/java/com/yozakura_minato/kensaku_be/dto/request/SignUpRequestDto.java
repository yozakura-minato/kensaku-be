package com.yozakura_minato.kensaku_be.dto.request;

import com.yozakura_minato.kensaku_be.annotation.isPasswordStrong.IsStrongPassword;
import com.yozakura_minato.kensaku_be.annotation.normalizedEmail.NormalizedEmail;
import com.yozakura_minato.kensaku_be.annotation.normalizedString.NormalizedString;
import com.yozakura_minato.kensaku_be.exception.message.SignUpException;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * <p>Attributes</p>
 * <ul>
 *     <li>{@code email} - (String)</li>
 *     <li>{@code displayName} - (String) not unique</li>
 *     <li>{@code password} - (String) plain password</i>
 * </ul>
 */
@Getter
@Setter
public class SignUpRequestDto {

    @NormalizedEmail
    @NotBlank(message = SignUpException.Email.nulls)
    @Email(message = SignUpException.Email.format)
    private String email;

    @NormalizedString
    @NotBlank(message = SignUpException.DisplayName.nulls)
    @Size(min = 3, max = 30, message = SignUpException.DisplayName.length)
    @Pattern(regexp = "^[A-za-z0-9 ]+$", message = SignUpException.DisplayName.format)
    private String displayName;

    @NormalizedString
    @NotBlank(message = SignUpException.Password.nulls)
    @Size(min = 8, max = 50, message = SignUpException.Password.length)
    @IsStrongPassword
    private String password;

}
