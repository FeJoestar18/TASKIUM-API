package com.taskium.project.Domain.Common.Exceptions.Task;

public class NoTasksFoundException extends RuntimeException {

    public NoTasksFoundException() {
        super("Nenhuma tarefa encontrada.");
    }

    public NoTasksFoundException(String message) {
        super(message);
    }
}

