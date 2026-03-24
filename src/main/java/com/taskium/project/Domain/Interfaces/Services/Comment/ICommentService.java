package com.taskium.project.Domain.Interfaces.Services.Comment;

import com.taskium.project.Domain.Entity.Comment;
import com.taskium.project.Domain.Entity.Task;
import com.taskium.project.Domain.Entity.User;

import java.util.List;

public interface ICommentService {
    Comment createComment(String description, User user, Task task);
    void deleteCommentById(Long id, User authenticatedUser);
    List<Comment> getCommentsByTaskId(Long taskId);
    List<Comment> getCommentsByUserId(Long userId);
}

