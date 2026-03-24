package com.taskium.project.Domain.Common.Exceptions.TaskStatus;

public class NoTaskStatusesFoundException extends RuntimeException {

    public NoTaskStatusesFoundException() {
        super("Nenhum status de tarefa encontrado.");
    }

    public NoTaskStatusesFoundException(String message) {
        super(message);
    }
}
