package ir.iranianCyber.todo.repository;

import ir.iranianCyber.todo.model.Todo;
import ir.iranianCyber.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findAllByUser(User user);
}
