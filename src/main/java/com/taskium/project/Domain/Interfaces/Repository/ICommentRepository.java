package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findByTaskId(Long taskId);
    List<Comment> findByUserId(Long userId);
    void deleteById(Long id);
    boolean existsById(Long id);
}

