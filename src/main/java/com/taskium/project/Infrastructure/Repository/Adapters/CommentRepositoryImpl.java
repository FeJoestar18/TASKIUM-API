package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.Comment;
import com.taskium.project.Domain.Interfaces.Repository.ICommentRepository;
import com.taskium.project.Infrastructure.Repository.JPA.CommentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements ICommentRepository {

    private final CommentJpaRepository jpaRepository;

    public CommentRepositoryImpl(CommentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return jpaRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Comment> findByTaskId(Long taskId) {
        return jpaRepository.findByTaskId(taskId);
    }

    @Override
    public List<Comment> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}

