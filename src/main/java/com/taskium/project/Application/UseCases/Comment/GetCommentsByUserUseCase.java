package com.taskium.project.Application.UseCases.Comment;

import com.taskium.project.Application.DTO.Comment.CommentResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Comment.NoCommentsFoundException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.Comment.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsByUserUseCase {

    private final ICommentService commentService;
    private final IUserRepository userRepository;

    public GetCommentsByUserUseCase(
            ICommentService commentService,
            IUserRepository userRepository
    ) {
        this.commentService = commentService;
        this.userRepository = userRepository;
    }

    public List<CommentResponseDTO> execute(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        var comments = commentService.getCommentsByUserId(userId);

        if (comments.isEmpty()) {
            throw new NoCommentsFoundException();
        }

        return comments.stream()
                .map(CommentResponseDTO::from)
                .toList();
    }
}

