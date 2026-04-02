package com.yozakura_minato.kensaku_be.dto.request;

import com.yozakura_minato.kensaku_be.annotation.normalizedEmail.NormalizedEmail;
import com.yozakura_minato.kensaku_be.annotation.normalizedString.NormalizedString;
import com.yozakura_minato.kensaku_be.exception.message.SignInException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code email} - (String)</li>
 *     <li>{@code password} - (String) plain password</li>
 * </ul>
 */
@Getter
@Setter
public class SignInRequestDto {

    @NormalizedEmail
    @NotBlank(message = SignInException.Email.nulls)
    @Email(message = SignInException.Email.format)
    private String email;

    @NormalizedString
    @NotBlank(message = SignInException.Password.nulls)
    private String password;

}
