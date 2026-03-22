package com.taskium.project.Infrastructure.Exceptions;

import com.taskium.project.Domain.Exceptions.User.CpflAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.User.EmailAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.User.PhoneNumberAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.User.RoleNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(CpflAlreadyExistsException.class)
    public ResponseEntity<?> handleCpfAlreadyExists(CpflAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
    public ResponseEntity<?> handlePhoneNUmberAlreadyExists(PhoneNumberAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleRoleNotFound(RoleNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

}