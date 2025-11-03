package ir.iranianCyber.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping
    public String index() {
        return "home";
    }

    @GetMapping("/list")
    public String list() {
        return "todoList";
    }

    @GetMapping("/add")
    public String add() {
        return "addTodo";
    }

    @GetMapping("/update")
    public String update() {
        return "updateTodo";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
