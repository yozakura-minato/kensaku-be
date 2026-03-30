package com.yozakura_minato.kensaku_be.exception.message;

/**
 * Exception messages for sign up
 */
public class SignUpException {

    public static class Email {

        public static final String nulls = "NULL_EMAIL.SIGN_UP.EXCEPTION";
        public static final String exits = "EMAIL_EXISTS.SIGN_UP.EXCEPTION";
        public static final String format = "EMAIL_FORMAT.SIGN_UP.EXCEPTION";

    }

    public static class DisplayName {

        public static final String nulls = "NULL_DISPLAY_NAME.SIGN_UP.EXCEPTION";
        public static final String format = "DISPLAY_NAME_FORMAT.SIGN_UP.EXCEPTION";
        public static final String length = "DISPLAY_NAME_LENGTH.SIGN_UP.EXCEPTION";

    }

    public static class Password {

        public static final String nulls = "NULL_PASSWORD.SIGN_UP.EXCEPTION";
        public static final String format = "PASSWORD_FORMAT.SIGN_UP.EXCEPTION";
        public static final String length = "PASSWORD_LENGTH.SIGN_UP.EXCEPTION";

    }

}
