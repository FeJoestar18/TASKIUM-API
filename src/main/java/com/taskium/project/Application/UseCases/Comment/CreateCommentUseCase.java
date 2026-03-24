package com.taskium.project.Application.UseCases.Comment;

import com.taskium.project.Application.DTO.Comment.CommentResponseDTO;
import com.taskium.project.Application.DTO.Comment.CreateCommentRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.Task.TaskNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.ITaskRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.Comment.ICommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCommentUseCase {

    private final ICommentService commentService;
    private final ITaskRepository taskRepository;
    private final IUserRepository userRepository;

    public CreateCommentUseCase(
            ICommentService commentService,
            ITaskRepository taskRepository,
            IUserRepository userRepository
    ) {
        this.commentService = commentService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommentResponseDTO execute(Long taskId, CreateCommentRequestDTO dto, String authenticatedEmail) {

        var user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new UserNotFoundException("Usuário autenticado não encontrado"));

        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        var comment = commentService.createComment(dto.getDescription(), user, task);

        return CommentResponseDTO.from(comment);
    }
}

