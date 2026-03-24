package com.taskium.project.Application.UseCases.Comment;

import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.Comment.ICommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteCommentByIdUseCase {

    private final ICommentService commentService;
    private final IUserRepository userRepository;

    public DeleteCommentByIdUseCase(
            ICommentService commentService,
            IUserRepository userRepository
    ) {
        this.commentService = commentService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void execute(Long commentId, String authenticatedEmail) {

        var user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new UserNotFoundException("Usuário autenticado não encontrado"));

        commentService.deleteCommentById(commentId, user);
    }
}

