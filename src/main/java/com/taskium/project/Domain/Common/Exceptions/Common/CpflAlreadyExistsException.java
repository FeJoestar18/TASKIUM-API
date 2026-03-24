package com.taskium.project.Domain.Common.Exceptions.Common;

public class CpflAlreadyExistsException extends  RuntimeException{

    public CpflAlreadyExistsException() {
        super("CPF já em uso");
    }

    public CpflAlreadyExistsException(String message) {
        super(message);
    }
}
