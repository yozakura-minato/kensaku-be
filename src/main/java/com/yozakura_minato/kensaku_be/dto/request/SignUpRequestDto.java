package com.yozakura_minato.kensaku_be.dto.request;

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
    private String password;

    /**
     * <p>Validate plain password strength</p>
     * <p><b>Criteria</b>: The trimmed password must contains at least 2 of below types:</p>
     * <ul>
     *     <li>Upper letter</li>
     *     <li>Lower letter</li>
     *     <li>Number</li>
     *     <li>Special character</li>
     * </ul>
     * @return Check result
     */
    @AssertTrue(message = SignUpException.Password.format)
    public boolean isPasswordStrength() {
        String normalizedPassword = password.trim();
        int typeNumber = 0;
        typeNumber += normalizedPassword.matches(".*[A-Z].*") ? 1 : 0;
        typeNumber += normalizedPassword.matches(".*[a-z].*") ? 1 : 0;
        typeNumber += normalizedPassword.matches(".*[0-9].*") ? 1 : 0;
        typeNumber += normalizedPassword.matches(".*[^A-Za-z0-9].*") ? 1 : 0;
        return typeNumber >= 2;
    }

}
