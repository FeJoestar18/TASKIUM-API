package com.taskium.project.Domain.Services.Comment;

import com.taskium.project.Domain.Common.Exceptions.Auth.UnauthorizedActionException;
import com.taskium.project.Domain.Common.Exceptions.Comment.CommentNotFoundException;
import com.taskium.project.Domain.Entity.Comment;
import com.taskium.project.Domain.Entity.Task;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Enums.RoleName;
import com.taskium.project.Domain.Interfaces.Repository.ICommentRepository;
import com.taskium.project.Domain.Interfaces.Services.Comment.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    private final ICommentRepository commentRepository;

    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(String description, User user, Task task) {

        Comment comment = Comment.builder()
                .description(description)
                .user(user)
                .task(task)
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(Long id, User authenticatedUser) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));

        boolean isOwner = comment.getUser().getId().equals(authenticatedUser.getId());
        boolean isAdmin = authenticatedUser.getUserDetail() != null
                && authenticatedUser.getUserDetail().getRole() != null
                && authenticatedUser.getUserDetail().getRole().getName() == RoleName.ADMIN;

        if (!isOwner && !isAdmin) {
            throw new UnauthorizedActionException();
        }

        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }

    @Override
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }
}

