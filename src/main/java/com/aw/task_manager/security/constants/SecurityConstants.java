package com.aw.task_manager.security.constants;

public class SecurityConstants {
    public static final String SECRET_KEY = "G-KaPdSgVkY/A?D*B&E)bQeThWmZq4t7w!z$C&F)Jp3s6v9y$@NcRfUjXn2r5u8;"; //Your secret should always be strong (uppercase, lowercase, numbers, symbols) so that nobody can potentially decode the signature.
    public static final int TOKEN_EXPIRATION = 7200000; // 7200000 milliseconds = 7200 seconds = 2 hours.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token 
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
    public static final String REGISTER_PATH = "/auth/register"; // Public path that clients can use to register.
}
