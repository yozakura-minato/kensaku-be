package com.yozakura_minato.kensaku_be.annotation.isPasswordStrong;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator implements ConstraintValidator<IsStrongPassword, String> {

    /**
     * <p>Validate plain password strength</p>
     * <p>The password must NOT be {@code null} and contain at least 2 of below types:</p>
     * <ul>
     *     <li>Upper letter</li>
     *     <li>Lower letter</li>
     *     <li>Number</li>
     *     <li>Special character</li>
     * </ul>
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     * @return Check result
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        int typeCount = 0;
        typeCount += value.matches(".*[A-Z].*") ? 1 : 0;
        typeCount += value.matches(".*[a-z].*") ? 1 : 0;
        typeCount += value.matches(".*[0-9].*") ? 1 : 0;
        typeCount += value.matches(".*[^A-Za-z0-9].*") ? 1 : 0;
        return typeCount >= 2;
    }

}
