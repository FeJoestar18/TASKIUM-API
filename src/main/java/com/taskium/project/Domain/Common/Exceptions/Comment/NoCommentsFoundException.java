package com.taskium.project.Domain.Common.Exceptions.Comment;

public class NoCommentsFoundException extends RuntimeException {

    public NoCommentsFoundException() {
        super("Nenhum comentário encontrado.");
    }

    public NoCommentsFoundException(String message) {
        super(message);
    }
}

