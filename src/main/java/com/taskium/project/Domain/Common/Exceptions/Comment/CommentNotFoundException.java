package com.taskium.project.Domain.Common.Exceptions.Comment;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long id) {
        super("Não existe comentário com esse ID: " + id);
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}

