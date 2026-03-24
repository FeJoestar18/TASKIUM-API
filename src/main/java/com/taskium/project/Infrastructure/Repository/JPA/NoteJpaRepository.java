package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteJpaRepository extends JpaRepository<Note, Long> {
}
