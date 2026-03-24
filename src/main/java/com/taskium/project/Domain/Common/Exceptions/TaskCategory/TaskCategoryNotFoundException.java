package com.taskium.project.Domain.Common.Exceptions.TaskCategory;

public class TaskCategoryNotFoundException extends RuntimeException {

    public TaskCategoryNotFoundException(Long id) {
        super("Não existe categoria de tarefa com esse ID: " + id);
    }

    public TaskCategoryNotFoundException(String message) {
        super(message);
    }
}

