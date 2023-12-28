package com.example.stmlabs.exception;

/**
 * Исключение используется для ошибок аутентификации и авторизациит.
 */
public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

}
