package com.yozakura_minato.kensaku_be.dto.request;

import com.yozakura_minato.kensaku_be.util.message.SignUpException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = SignUpException.Email.nulls)
    @Email(message = SignUpException.Email.format)
    private String email;

    @NotBlank(message = SignUpException.DisplayName.nulls)
    @Size(min = 3, max = 30, message = SignUpException.DisplayName.length) // Only letters (lower/upper), numbers and space are allowed
    @Pattern(regexp = "^[A-za-z0-9 ]+$", message = SignUpException.DisplayName.format)
    private String displayName;

    @NotBlank(message = SignUpException.Password.nulls)
    @Size(min = 8, max = 50, message = SignUpException.Password.length)
    private String password;

}
