package com.taskium.project.Infrastructure.Exceptions;

import com.taskium.project.Application.DTO.Common.ErrorResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Comment.CommentNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Comment.NoCommentsFoundException;
import com.taskium.project.Domain.Common.Exceptions.Common.CpflAlreadyExistsException;
import com.taskium.project.Domain.Common.Exceptions.Common.EmailAlreadyExistsException;
import com.taskium.project.Domain.Common.Exceptions.Event.EventDateInvalidException;
import com.taskium.project.Domain.Common.Exceptions.Event.EventNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.EventTypeNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.EventUserNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.NoEventsFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.NoEventTypesFoundException;
import com.taskium.project.Domain.Common.Exceptions.Note.NoNotesFoundException;
import com.taskium.project.Domain.Common.Exceptions.Note.NoteNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Note.UnauthorizedNoteAccessException;
import com.taskium.project.Domain.Common.Exceptions.Event.NoParticipationStatusesFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.ParticipationStatusNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Regulation.InactiveRegulationException;
import com.taskium.project.Domain.Common.Exceptions.Regulation.NoAcceptedRegulationsFoundException;
import com.taskium.project.Domain.Common.Exceptions.Regulation.NoRegulationsFoundException;
import com.taskium.project.Domain.Common.Exceptions.TaskCategory.NoTaskCategoriesFoundException;
import com.taskium.project.Domain.Common.Exceptions.TaskStatus.NoTaskStatusesFoundException;
import com.taskium.project.Domain.Common.Exceptions.Task.NoTasksFoundException;
import com.taskium.project.Domain.Common.Exceptions.User.NoUsersFoundException;
import com.taskium.project.Domain.Common.Exceptions.Common.PhoneNumberAlreadyExistsException;
import com.taskium.project.Domain.Common.Exceptions.Regulation.RegulationAlreadyAcceptedException;
import com.taskium.project.Domain.Common.Exceptions.Regulation.RegulationNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Role.RoleNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.TaskCategory.TaskCategoryNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Task.TaskNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.TaskStatus.TaskStatusNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Auth.AccessDeniedException;
import com.taskium.project.Domain.Common.Exceptions.Auth.InvalidTokenException;
import com.taskium.project.Domain.Common.Exceptions.Auth.UnauthorizedActionException;
import com.taskium.project.Domain.Common.Exceptions.Status.StatusNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.UnauthorizedEventActionException;
import com.taskium.project.Domain.Common.Exceptions.Event.UserAlreadyInEventException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //  VALIDATION (400)

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (current, replacement) -> current
                ));

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validação falhou",
                "Campos inválidos",
                request.getRequestURI(),
                fieldErrors
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request
    ) {

        Map<String, String> violations = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage(),
                        (current, replacement) -> current
                ));

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Violação de constraint",
                ex.getMessage(),
                request.getRequestURI(),
                violations
        );
    }

    //  UNAUTHORIZED (401)

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidToken(
            InvalidTokenException ex,
            HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Token inválido",
                ex.getMessage(),
                request.getRequestURI(),
                Map.of()
        );
    }

    //  FORBIDDEN (403)

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.FORBIDDEN,
                "Acesso negado",
                ex.getMessage(),
                request.getRequestURI(),
                Map.of()
        );
    }

    //  CONFLICT (409)

    @ExceptionHandler({
            EmailAlreadyExistsException.class,
            CpflAlreadyExistsException.class,
            PhoneNumberAlreadyExistsException.class,
            RegulationAlreadyAcceptedException.class,
            UserAlreadyInEventException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleConflict(
            RuntimeException ex,
            HttpServletRequest request
    ) {

        return buildErrorResponse(
                HttpStatus.CONFLICT,
                "Conflito de dados",
                ex.getMessage(),
                request.getRequestURI(),
                Map.of()
        );
    }

    //  NOT FOUND (404)
    @ExceptionHandler({
            RoleNotFoundException.class,
            UserNotFoundException.class,
            NoUsersFoundException.class,
            RegulationNotFoundException.class,
            NoRegulationsFoundException.class,
            NoAcceptedRegulationsFoundException.class,
            EventNotFoundException.class,
            NoEventsFoundException.class,
            EventTypeNotFoundException.class,
            NoEventTypesFoundException.class,
            ParticipationStatusNotFoundException.class,
            NoParticipationStatusesFoundException.class,
            EventUserNotFoundException.class,
            TaskNotFoundException.class,
            NoTasksFoundException.class,
            TaskCategoryNotFoundException.class,
            NoTaskCategoriesFoundException.class,
            TaskStatusNotFoundException.class,
            NoTaskStatusesFoundException.class,
            NoteNotFoundException.class,
            NoNotesFoundException.class,
            CommentNotFoundException.class,
            NoCommentsFoundException.class,
            StatusNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleNotFound(
            RuntimeException ex,
            HttpServletRequest request
    ) {

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Registro não encontrado",
                ex.getMessage(),
                request.getRequestURI(),
                Map.of()
        );
    }

    @ExceptionHandler({
            UnauthorizedActionException.class,
            UnauthorizedNoteAccessException.class,
            UnauthorizedEventActionException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleForbidden(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.FORBIDDEN,
                "Acesso negado",
                ex.getMessage(),
                request.getRequestURI(),
                Map.of()
        );
    }

    @ExceptionHandler({
            InactiveRegulationException.class,
            EventDateInvalidException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleBusinessRule(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Regra de negócio inválida",
                ex.getMessage(),
                request.getRequestURI(),
                Map.of()
        );
    }

    //  GENERIC (500)

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {

        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno",
                ex.getMessage(),
                request.getRequestURI(),
                Map.of()
        );
    }

    // BUILDER

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(
            HttpStatus status,
            String error,
            String message,
            String path,
            Map<String, String> details
    ) {

        ErrorResponseDTO body = ErrorResponseDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(error)
                .message(message)
                .path(path)
                .details(details)
                .build();

        return ResponseEntity.status(status).body(body);
    }
}
