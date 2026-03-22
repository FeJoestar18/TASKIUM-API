package com.taskium.project.Domain.Exceptions.Auth;

public class CpflAlreadyExistsException extends  RuntimeException{

    public CpflAlreadyExistsException() {
        super("CPF já em uso");
    }

    public CpflAlreadyExistsException(String message) {
        super(message);
    }
}
