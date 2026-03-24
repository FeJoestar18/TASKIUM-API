package com.taskium.project.Application.DTO.Note;

import com.taskium.project.Domain.Entity.Note;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoteListResponseDTO {
    private final Long id;
    private final String name;
    private final String description;
    private final LocalDateTime updatedAt;

    public static NoteListResponseDTO from(Note note) {
        return NoteListResponseDTO.builder()
                .id(note.getId())
                .name(note.getName())
                .description(note.getDescription())
                .updatedAt(note.getUpdatedAt())
                .build();
    }
}
