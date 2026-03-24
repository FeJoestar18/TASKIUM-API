package com.taskium.project.Domain.Common.Exceptions.TaskStatus;

public class TaskStatusNotFoundException extends RuntimeException {

    public TaskStatusNotFoundException(Long id) {
        super("Não existe status de tarefa com esse ID: " + id);
    }

    public TaskStatusNotFoundException(String message) {
        super(message);
    }
}

