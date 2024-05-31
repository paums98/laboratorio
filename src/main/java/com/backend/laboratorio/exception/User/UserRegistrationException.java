package com.backend.laboratorio.exception.User;

public class UserRegistrationException extends RuntimeException
{
    public UserRegistrationException() {
        super();
    }

    public UserRegistrationException(String message) {
        super(message);
    }
}
