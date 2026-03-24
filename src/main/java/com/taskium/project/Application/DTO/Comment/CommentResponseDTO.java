package com.taskium.project.Application.DTO.Comment;

import com.taskium.project.Domain.Entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDTO {

    private final Long id;
    private final String description;
    private final Long userId;
    private final String userName;
    private final Long taskId;
    private final String taskName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static CommentResponseDTO from(Comment comment) {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .description(comment.getDescription())
                .userId(comment.getUser() != null ? comment.getUser().getId() : null)
                .userName(comment.getUser() != null ? comment.getUser().getName() : null)
                .taskId(comment.getTask() != null ? comment.getTask().getId() : null)
                .taskName(comment.getTask() != null ? comment.getTask().getName() : null)
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}

