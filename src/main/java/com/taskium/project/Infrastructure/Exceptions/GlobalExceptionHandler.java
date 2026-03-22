package com.taskium.project.Infrastructure.Exceptions;

import com.taskium.project.Domain.Exceptions.Auth.CpflAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.Auth.EmailAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.Auth.PhoneNumberAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.Auth.RoleNotFoundException;
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