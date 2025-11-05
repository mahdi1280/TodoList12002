package ir.iranianCyber.todo.controller;

import ir.iranianCyber.todo.dto.UserDto;
import ir.iranianCyber.todo.model.User;
import ir.iranianCyber.todo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        List<User> users = userService.findAll();
        return users.stream().map(u -> new UserDto(u.getUsername(), u.getRole()))
                .collect(Collectors.toList());
    }
}
