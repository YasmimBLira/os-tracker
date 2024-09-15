package com.br.inverame.exception;

public class ClientValidationException extends RuntimeException {
    public ClientValidationException(String message) {
        super(message);
    }
}
