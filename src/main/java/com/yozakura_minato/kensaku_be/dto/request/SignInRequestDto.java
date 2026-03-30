package com.yozakura_minato.kensaku_be.dto.request;

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

    @NotBlank(message = SignInException.Email.nulls)
    @Email(message = SignInException.Email.format)
    private String email;

    @NotBlank(message = SignInException.Password.nulls)
    private String password;

    /**
     * Trim {@code password}
     */
    public void normalize() {
        password = password.trim();
    }

}
