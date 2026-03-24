package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserNoteJpaRepository extends JpaRepository<UserNote, Long> {
    Optional<UserNote> findByNote_Id(Long noteId);
    List<UserNote> findByUser_Id(Long userId);
    void deleteByNote_Id(Long noteId);
}
