package com.taskium.project.Domain.Common.Validators;

import com.taskium.project.Domain.Common.Exceptions.Note.UnauthorizedNoteAccessException;
import org.springframework.stereotype.Component;

@Component
public class NoteValidator {

    public void validateOwnership(Long ownerUserId, Long authenticatedUserId) {
        if (!ownerUserId.equals(authenticatedUserId)) {
            throw new UnauthorizedNoteAccessException();
        }
    }
}
