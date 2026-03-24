package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.UserNote;
import com.taskium.project.Domain.Interfaces.Repository.IUserNoteRepository;
import com.taskium.project.Infrastructure.Repository.JPA.UserNoteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserNoteRepositoryImpl implements IUserNoteRepository {

    private final UserNoteJpaRepository userNoteJpaRepository;

    public UserNoteRepositoryImpl(UserNoteJpaRepository userNoteJpaRepository) {
        this.userNoteJpaRepository = userNoteJpaRepository;
    }

    @Override
    public UserNote save(UserNote userNote) {
        return userNoteJpaRepository.save(userNote);
    }

    @Override
    public Optional<UserNote> findByNoteId(Long noteId) {
        return userNoteJpaRepository.findByNote_Id(noteId);
    }

    @Override
    public List<UserNote> findByUserId(Long userId) {
        return userNoteJpaRepository.findByUser_Id(userId);
    }

    @Override
    public void deleteByNoteId(Long noteId) {
        userNoteJpaRepository.deleteByNote_Id(noteId);
    }
}
