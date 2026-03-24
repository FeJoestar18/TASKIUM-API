package com.taskium.project.Application.UseCases.Comment;

import com.taskium.project.Application.DTO.Comment.CommentResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Comment.NoCommentsFoundException;
import com.taskium.project.Domain.Common.Exceptions.Task.TaskNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.ITaskRepository;
import com.taskium.project.Domain.Interfaces.Services.Comment.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsByTaskUseCase {

    private final ICommentService commentService;
    private final ITaskRepository taskRepository;

    public GetCommentsByTaskUseCase(
            ICommentService commentService,
            ITaskRepository taskRepository
    ) {
        this.commentService = commentService;
        this.taskRepository = taskRepository;
    }

    public List<CommentResponseDTO> execute(Long taskId) {

        if (!taskRepository.existsById(taskId)) {
            throw new TaskNotFoundException(taskId);
        }

        var comments = commentService.getCommentsByTaskId(taskId);

        if (comments.isEmpty()) {
            throw new NoCommentsFoundException();
        }

        return comments.stream()
                .map(CommentResponseDTO::from)
                .toList();
    }
}

