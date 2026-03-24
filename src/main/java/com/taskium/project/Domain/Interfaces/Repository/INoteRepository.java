package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Note;

import java.util.List;
import java.util.Optional;

public interface INoteRepository {
    Note save(Note note);
    Optional<Note> findById(Long id);
    List<Note> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
