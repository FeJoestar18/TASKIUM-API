package com.taskium.project.Api.Controllers.Task;

import com.taskium.project.Application.DTO.Task.TaskRequestDTO;
import com.taskium.project.Application.DTO.Task.TaskResponseDTO;
import com.taskium.project.Application.UseCases.Task.CreateTaskUseCase;
import com.taskium.project.Application.UseCases.Task.DeleteTaskByIdUseCase;
import com.taskium.project.Application.UseCases.Task.GetAllTasksUseCase;
import com.taskium.project.Application.UseCases.Task.UpdateTaskUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskByIdUseCase deleteTaskByIdUseCase;
    private final GetAllTasksUseCase getAllTasksUseCase;

    public TaskController(
            CreateTaskUseCase createTaskUseCase,
            UpdateTaskUseCase updateTaskUseCase,
            DeleteTaskByIdUseCase deleteTaskByIdUseCase,
            GetAllTasksUseCase getAllTasksUseCase
    ) {
        this.createTaskUseCase = createTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskByIdUseCase = deleteTaskByIdUseCase;
        this.getAllTasksUseCase = getAllTasksUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_TASK')")
    public ResponseEntity<TaskResponseDTO> create(@Valid @RequestBody TaskRequestDTO dto) {

        var response = createTaskUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_TASK')")
    public ResponseEntity<TaskResponseDTO> updateById(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO dto) {

        var response = updateTaskUseCase.execute(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_TASK')")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        deleteTaskByIdUseCase.execute(id);

        return ResponseEntity.ok("Tarefa deletada com sucesso");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_TASK')")
    public ResponseEntity<List<TaskResponseDTO>> getAll() {

        return ResponseEntity.ok(getAllTasksUseCase.execute());
    }
}
