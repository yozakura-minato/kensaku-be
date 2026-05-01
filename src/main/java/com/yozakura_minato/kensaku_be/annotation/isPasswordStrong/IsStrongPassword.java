package com.yozakura_minato.kensaku_be.annotation.isPasswordStrong;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * <p>Validate password strength</p>
 * <p>Rule: The password must be NOT {@code null} and contains at least 2 of below character types:</p>
 * <ul>
 *     <li>Upper letter</li>
 *     <li>Lower letter</li>
 *     <li>Number</li>
 *     <li>Special character</li>
 * </ul>
 */
@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsStrongPassword {

    String message() default "Password is weak";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
