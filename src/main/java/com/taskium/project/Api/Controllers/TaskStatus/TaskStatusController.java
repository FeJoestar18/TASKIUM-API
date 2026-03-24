package com.taskium.project.Api.Controllers.TaskStatus;

import com.taskium.project.Application.DTO.TaskStatus.TaskStatusRequestDTO;
import com.taskium.project.Application.DTO.TaskStatus.TaskStatusResponseDTO;
import com.taskium.project.Application.UseCases.TaskStatus.CreateTaskStatusUseCase;
import com.taskium.project.Application.UseCases.TaskStatus.DeleteTaskStatusByIdUseCase;
import com.taskium.project.Application.UseCases.TaskStatus.GetAllTaskStatusesUseCase;
import com.taskium.project.Application.UseCases.TaskStatus.UpdateTaskStatusUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-status")
public class TaskStatusController {

    private final CreateTaskStatusUseCase createTaskStatusUseCase;
    private final UpdateTaskStatusUseCase updateTaskStatusUseCase;
    private final DeleteTaskStatusByIdUseCase deleteTaskStatusByIdUseCase;
    private final GetAllTaskStatusesUseCase getAllTaskStatusesUseCase;

    public TaskStatusController(
            CreateTaskStatusUseCase createTaskStatusUseCase,
            UpdateTaskStatusUseCase updateTaskStatusUseCase,
            DeleteTaskStatusByIdUseCase deleteTaskStatusByIdUseCase,
            GetAllTaskStatusesUseCase getAllTaskStatusesUseCase
    ) {
        this.createTaskStatusUseCase = createTaskStatusUseCase;
        this.updateTaskStatusUseCase = updateTaskStatusUseCase;
        this.deleteTaskStatusByIdUseCase = deleteTaskStatusByIdUseCase;
        this.getAllTaskStatusesUseCase = getAllTaskStatusesUseCase;
    }

    @PostMapping
    public ResponseEntity<TaskStatusResponseDTO> create(@Valid @RequestBody TaskStatusRequestDTO dto) {

        var response = createTaskStatusUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskStatusResponseDTO> updateById(@PathVariable Long id, @Valid @RequestBody TaskStatusRequestDTO dto) {

        var response = updateTaskStatusUseCase.execute(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        deleteTaskStatusByIdUseCase.execute(id);

        return ResponseEntity.ok("Status deletado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<TaskStatusResponseDTO>> getAll() {

        return ResponseEntity.ok(getAllTaskStatusesUseCase.execute());
    }
}
