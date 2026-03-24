package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.Note;
import com.taskium.project.Domain.Interfaces.Repository.INoteRepository;
import com.taskium.project.Infrastructure.Repository.JPA.NoteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoteRepositoryImpl implements INoteRepository {

    private final NoteJpaRepository noteJpaRepository;

    public NoteRepositoryImpl(NoteJpaRepository noteJpaRepository) {
        this.noteJpaRepository = noteJpaRepository;
    }

    @Override
    public Note save(Note note) {
        return noteJpaRepository.save(note);
    }

    @Override
    public Optional<Note> findById(Long id) {
        return noteJpaRepository.findById(id);
    }

    @Override
    public List<Note> findAll() {
        return noteJpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        noteJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return noteJpaRepository.existsById(id);
    }
}
