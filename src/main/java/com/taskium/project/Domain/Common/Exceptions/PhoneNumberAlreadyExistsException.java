package com.taskium.project.Domain.Common.Exceptions;

public class PhoneNumberAlreadyExistsException extends RuntimeException {

    public PhoneNumberAlreadyExistsException() {
        super("Phone number already exists.");
    }
    public PhoneNumberAlreadyExistsException(String message) {
        super(message);
    }
}
