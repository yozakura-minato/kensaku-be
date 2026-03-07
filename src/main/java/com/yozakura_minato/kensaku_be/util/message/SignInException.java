package com.yozakura_minato.kensaku_be.util.message;

/**
 * Exception message for sign in
 */
public class SignInException {

    public static class Email {

        public static final String nulls = "NULL_EMAIL.SIGN_IN.EXCEPTION";
        public static final String notFound = "EMAIL_NOTFOUND.SIGN_IN.EXCEPTION";
        public static final String format = "EMAIL_FORMAT.SIGN_IN.EXCEPTION";

    }

    public static class Password {

        public static final String nulls = "NULL_PASSWORD.SIGN_IN.EXCEPTION";

    }

}
