package com.yozakura_minato.kensaku_be.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

/**
 * Response DTO for sign up
 */
public class SignUpRequestDto {

    // ============ ATTRIBUTES ============ //
    /**
     * blank => NULL_EMAIL.SIGN_UP.EXCEPTION |
     * wrong format => EMAIL_FORMAT.SIGN_UP.EXCEPTION
     */
    @NotBlank(message = "NULL_EMAIL.SIGN_UP.EXCEPTION")
    @Email(message = "EMAIL_FORMAT.SIGN_UP.EXCEPTION")
    private String email;

    /**
     * blank => NULL_DISPLAY_NAME.SIGN_UP.EXCEPTION |
     * wrong size [3, 30] => DISPLAY_NAME_LENGTH.SIGN_UP.EXCEPTION
     */
    @NotBlank(message = "NULL_DISPLAY_NAME.SIGN_UP.EXCEPTION")
    @Size(min = 3, max = 30, message = "DISPLAY_NAME_LENGTH.SIGN_UP.EXCEPTION")
    @Pattern(regexp = "^[A-za-z0-9 ]+$", message = "DISPLAY_NAME_FORMAT.SIGN_UP.EXCEPTION")
    private String displayName;

    /**
     * blank => NULL_PASSWORD.SIGN_UP.EXCEPTION |
     * wrong size [8, 50] => PASSWORD_LENGTH.SIGN_UP.EXCEPTION
     */
    @NotBlank(message = "NULL_PASSWORD.SIGN_UP.EXCEPTION")
    @Size(min = 8, max = 50, message = "PASSWORD_LENGTH.SIGN_UP.EXCEPTION")
    private String password;

    // ============ GETTERS & SETTERS ============ //
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
