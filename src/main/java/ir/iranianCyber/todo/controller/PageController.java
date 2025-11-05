package ir.iranianCyber.todo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final PasswordEncoder passwordEncoder;

    public PageController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String index() {
        System.out.println(passwordEncoder.encode("test"));
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

    @GetMapping("/admin/user")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String adminUser() {
        return "users";
    }
}
