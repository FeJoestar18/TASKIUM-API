package com.taskium.project.Api.Controllers.TaskCategory;

import com.taskium.project.Application.DTO.TaskCategory.TaskCategoryRequestDTO;
import com.taskium.project.Application.DTO.TaskCategory.TaskCategoryResponseDTO;
import com.taskium.project.Application.UseCases.TaskCategory.CreateTaskCategoryUseCase;
import com.taskium.project.Application.UseCases.TaskCategory.DeleteTaskCategoryByIdUseCase;
import com.taskium.project.Application.UseCases.TaskCategory.GetAllTaskCategoriesUseCase;
import com.taskium.project.Application.UseCases.TaskCategory.UpdateTaskCategoryUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-categories")
public class TaskCategoryController {

    private final CreateTaskCategoryUseCase createTaskCategoryUseCase;
    private final UpdateTaskCategoryUseCase updateTaskCategoryUseCase;
    private final DeleteTaskCategoryByIdUseCase deleteTaskCategoryByIdUseCase;
    private final GetAllTaskCategoriesUseCase getAllTaskCategoriesUseCase;

    public TaskCategoryController(
            CreateTaskCategoryUseCase createTaskCategoryUseCase,
            UpdateTaskCategoryUseCase updateTaskCategoryUseCase,
            DeleteTaskCategoryByIdUseCase deleteTaskCategoryByIdUseCase,
            GetAllTaskCategoriesUseCase getAllTaskCategoriesUseCase
    ) {
        this.createTaskCategoryUseCase = createTaskCategoryUseCase;
        this.updateTaskCategoryUseCase = updateTaskCategoryUseCase;
        this.deleteTaskCategoryByIdUseCase = deleteTaskCategoryByIdUseCase;
        this.getAllTaskCategoriesUseCase = getAllTaskCategoriesUseCase;
    }

    @PostMapping
    public ResponseEntity<TaskCategoryResponseDTO> create(@Valid @RequestBody TaskCategoryRequestDTO dto) {

        var response = createTaskCategoryUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskCategoryResponseDTO> updateById(@PathVariable Long id, @Valid @RequestBody TaskCategoryRequestDTO dto) {

        var response = updateTaskCategoryUseCase.execute(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        deleteTaskCategoryByIdUseCase.execute(id);

        return ResponseEntity.ok("Categoria deletada com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<TaskCategoryResponseDTO>> getAll() {

        return ResponseEntity.ok(getAllTaskCategoriesUseCase.execute());
    }
}
