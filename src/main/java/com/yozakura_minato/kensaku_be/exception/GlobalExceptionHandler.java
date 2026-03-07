package com.yozakura_minato.kensaku_be.exception;

import com.yozakura_minato.kensaku_be.util.message.AuthenticationException;
import com.yozakura_minato.kensaku_be.util.message.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Customized exception handler for business logic
     * @param exception (RuntimeException)
     * @return exception message - (String)
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handlingRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    /**
     * Customized exception handler for method argument validation
     * @param exception (MethodArgumentNotValidException)
     * @return GeneralException.unknown - (String) in case of empty not valid exception
     * <br>
     * fist error message - (String) in case of not empty not valid exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getFieldErrors();
        if(errors.isEmpty()) {
            return ResponseEntity.badRequest().body(GeneralException.unknown);
        }
        return ResponseEntity.badRequest().body(errors.getFirst().getDefaultMessage());
    }

    /**
     * Customized exception handler for bad credentials
     * @param exception (BadCredentialsException)
     * @return AuthenticationException.badCredentials - (String)
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handlingBadCredentialsException(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthenticationException.badCredentials);
    }

}
