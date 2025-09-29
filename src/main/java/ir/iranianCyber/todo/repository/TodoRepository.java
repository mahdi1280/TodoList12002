package ir.iranianCyber.todo.repository;

import ir.iranianCyber.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
