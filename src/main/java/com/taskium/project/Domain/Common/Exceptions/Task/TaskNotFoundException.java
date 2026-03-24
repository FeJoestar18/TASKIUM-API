package com.taskium.project.Domain.Common.Exceptions.Task;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Não existe tarefa com esse ID: " + id);
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}

