package com.taskium.project.Api.Controllers.Comment;

import com.taskium.project.Application.DTO.Comment.CommentResponseDTO;
import com.taskium.project.Application.DTO.Comment.CreateCommentRequestDTO;
import com.taskium.project.Application.UseCases.Comment.CreateCommentUseCase;
import com.taskium.project.Application.UseCases.Comment.DeleteCommentByIdUseCase;
import com.taskium.project.Application.UseCases.Comment.GetCommentsByTaskUseCase;
import com.taskium.project.Application.UseCases.Comment.GetCommentsByUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CreateCommentUseCase createCommentUseCase;
    private final DeleteCommentByIdUseCase deleteCommentByIdUseCase;
    private final GetCommentsByTaskUseCase getCommentsByTaskUseCase;
    private final GetCommentsByUserUseCase getCommentsByUserUseCase;

    public CommentController(
            CreateCommentUseCase createCommentUseCase,
            DeleteCommentByIdUseCase deleteCommentByIdUseCase,
            GetCommentsByTaskUseCase getCommentsByTaskUseCase,
            GetCommentsByUserUseCase getCommentsByUserUseCase
    ) {
        this.createCommentUseCase = createCommentUseCase;
        this.deleteCommentByIdUseCase = deleteCommentByIdUseCase;
        this.getCommentsByTaskUseCase = getCommentsByTaskUseCase;
        this.getCommentsByUserUseCase = getCommentsByUserUseCase;
    }

    @PostMapping("/tasks/{taskId}/comments")
    @PreAuthorize("hasAuthority('CREATE_COMMENT')")
    public ResponseEntity<CommentResponseDTO> create(
            @PathVariable Long taskId,
            @Valid @RequestBody CreateCommentRequestDTO dto,
            Authentication authentication
    ) {

        var response = createCommentUseCase.execute(taskId, dto, authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/comments/{id}")
    @PreAuthorize("hasAuthority('DELETE_COMMENT')")
    public ResponseEntity<String> deleteById(
            @PathVariable Long id,
            Authentication authentication
    ) {

        deleteCommentByIdUseCase.execute(id, authentication.getName());

        return ResponseEntity.ok("Comentário deletado com sucesso");
    }

    @GetMapping("/tasks/{taskId}/comments")
    @PreAuthorize("hasAuthority('VIEW_COMMENT')")
    public ResponseEntity<List<CommentResponseDTO>> getByTask(@PathVariable Long taskId) {

        return ResponseEntity.ok(getCommentsByTaskUseCase.execute(taskId));
    }

    @GetMapping("/users/{userId}/comments")
    @PreAuthorize("hasAuthority('VIEW_COMMENT')")
    public ResponseEntity<List<CommentResponseDTO>> getByUser(@PathVariable Long userId) {

        return ResponseEntity.ok(getCommentsByUserUseCase.execute(userId));
    }
}
