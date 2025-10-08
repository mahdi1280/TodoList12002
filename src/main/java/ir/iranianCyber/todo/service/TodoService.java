package ir.iranianCyber.todo.service;

import ir.iranianCyber.todo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoService {

    void save(Todo todo);

    void changeComplete(Todo todo, boolean complete);

    Todo findById(Integer id);

    Page<Todo> findAll(Pageable pageable);

    List<Todo> findAll();

    void deleteTodoById(Integer id);
}
