package com.taskium.project.Application.DTO.Note;

import com.taskium.project.Domain.Entity.Note;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoteResponseDTO {
    private final Long id;
    private final String name;
    private final String description;
    private final String note;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static NoteResponseDTO from(Note note) {
        return NoteResponseDTO.builder()
                .id(note.getId())
                .name(note.getName())
                .description(note.getDescription())
                .note(note.getNote())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }
}
