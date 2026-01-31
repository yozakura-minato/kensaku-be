package com.yozakura_minato.kensaku_be.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Response DTO for users
 */
public class UserRequestDto {

    // ============ ATTRIBUTES ============ //
    /**
     * blank => blankEmail.validaion.exception |
     * wrong format => emailFormat.validaion.exception
     */
    @NotBlank(message = "blankEmail.validaion.exception")
    @Email(message = "emailFormat.validaion.exception")
    private String email;

    /**
     * blank => blankEmail.validaion.exception |
     * wrong size [3, 30] => displayNameSize.validaion.exception
     */
    @NotBlank(message = "blankEmail.validaion.exception")
    @Size(min = 3, max = 30, message = "displayNameSize.validaion.exception")
    private String displayName;

    /**
     * blank => blankPassword.validaion.exception |
     * wrong size [8, 50] => passwordSize.validaion.exception
     */
    @NotBlank(message = "blankPassword.validaion.exception")
    @Size(min = 8, max = 50, message = "passwordSize.validaion.exception")
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
