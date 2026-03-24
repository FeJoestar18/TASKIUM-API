package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.UserNote;

import java.util.List;
import java.util.Optional;

public interface IUserNoteRepository {
    UserNote save(UserNote userNote);
    Optional<UserNote> findByNoteId(Long noteId);
    List<UserNote> findByUserId(Long userId);
    void deleteByNoteId(Long noteId);
}
