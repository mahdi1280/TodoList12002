package ir.iranianCyber.todo.controller;

import ir.iranianCyber.todo.dto.TodoDto;
import ir.iranianCyber.todo.model.Todo;
import ir.iranianCyber.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setDescription(todoDto.description());
        todo.setTitle(todoDto.title());
        todo.setCompleted(Boolean.FALSE);
        todoService.save(todo);
        return ResponseEntity.ok(todo.getId());
    }
}
