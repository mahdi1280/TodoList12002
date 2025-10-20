package ir.iranianCyber.todo.controller;

import ir.iranianCyber.todo.dto.TodoDto;
import ir.iranianCyber.todo.model.Todo;
import ir.iranianCyber.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        List<Todo> todos = todoService.findAll();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/change-status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable int id) {
        Todo todo = todoService.findById(id);
        todo.setCompleted(!todo.getCompleted());
        todoService.save(todo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Todo todo = todoService.findById(id);
        todoService.deleteTodoById(id);
        return ResponseEntity.ok().build();
    }
}
