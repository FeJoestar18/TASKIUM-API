package com.taskium.project.Domain.Common.Exceptions.TaskCategory;

public class NoTaskCategoriesFoundException extends RuntimeException {

    public NoTaskCategoriesFoundException() {
        super("Nenhuma categoria de tarefa encontrada.");
    }

    public NoTaskCategoriesFoundException(String message) {
        super(message);
    }
}

