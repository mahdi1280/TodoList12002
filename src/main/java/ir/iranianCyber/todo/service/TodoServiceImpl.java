package ir.iranianCyber.todo.service;

import ir.iranianCyber.todo.model.Todo;
import ir.iranianCyber.todo.myException.NotFoundException;
import ir.iranianCyber.todo.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void changeComplete(Todo todo, boolean complete) {
        todo.setCompleted(complete);
        todoRepository.save(todo);
    }

    @Override
    public Todo findById(Integer id) {
        return todoRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("todo.not.found"));
    }

    @Override
    public Page<Todo> findAll(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public void deleteTodoById(Integer id) {
        todoRepository.deleteById(id);
    }
}
