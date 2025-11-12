package ir.iranianCyber.todo.controller;

import ir.iranianCyber.todo.dto.TodoDto;
import ir.iranianCyber.todo.model.Todo;
import ir.iranianCyber.todo.service.TodoService;
import ir.iranianCyber.todo.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody @Valid TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setDescription(todoDto.description());
        todo.setTitle(todoDto.title());
        todo.setCompleted(Boolean.FALSE);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ir.iranianCyber.todo.model.User myUser = userService.findByUsername(user.getUsername());
        todo.setUser(myUser);
        todoService.save(todo);
        return ResponseEntity.ok(todo.getId());
    }

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ir.iranianCyber.todo.model.User myUser = userService.findByUsername(user.getUsername());
        List<Todo> todos = todoService.findAll(myUser);
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
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!todo.getUser().getUsername().equals(user.getUsername()) ) {
            throw new RuntimeException("todo not found");
        }
        todoService.deleteTodoById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Integer> update(@PathVariable int id, @RequestBody @Valid TodoDto todoDto) {
       Todo todo =  todoService.findById(id);
       todo.setTitle(todoDto.title());
       todo.setDescription(todoDto.description());
       todoService.save(todo);
       return ResponseEntity.ok(todo.getId());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Todo>  findById(@PathVariable int id) {
        Todo todo = todoService.findById(id);
        return ResponseEntity.ok(todo);
    }
}
