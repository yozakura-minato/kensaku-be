package com.yozakura_minato.kensaku_be.util.helper;

import com.yozakura_minato.kensaku_be.util.message.SignUpException;

/**
 * <p>Helper function to validate Request DTOs.</p>
 */
public class KenSakuValidator {

    /**
     * <p>The {@code password} must contain at least 2 of the following character types:</p>
     * <ul>
     *     <li>Uppercase letter</li>
     *     <li>Lowercase letter</li>
     *     <li>Number</li>
     *     <li>Special characters</li>
     * </ul>
     * @param password
     * @throws SignUpException password format, if validation failed
     */
    public static void validatePassword(String password) {
        int typeNumber = 0;
        typeNumber += password.matches(".*[A-Z].*") ? 1 : 0;
        typeNumber += password.matches(".*[a-z].*") ? 1 : 0;
        typeNumber += password.matches(".*[0-9].*") ? 1 : 0;
        typeNumber += password.matches(".*[^A-Za-z0-9].*") ? 1 : 0;

        if (typeNumber < 2) {
            throw new RuntimeException(SignUpException.Password.format);
        }
    }

}
