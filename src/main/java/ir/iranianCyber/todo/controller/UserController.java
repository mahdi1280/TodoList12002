package ir.iranianCyber.todo.controller;

import ir.iranianCyber.todo.dto.RegisterDto;
import ir.iranianCyber.todo.dto.UserDto;
import ir.iranianCyber.todo.model.Role;
import ir.iranianCyber.todo.model.User;
import ir.iranianCyber.todo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @ResponseBody
    public List<UserDto> getAll() {
        List<User> users = userService.findAll();
        return users.stream().map(u -> new UserDto(u.getUsername(), u.getRole()))
                .collect(Collectors.toList());
    }

    @PostMapping("/register")
    public String register(RegisterDto registerDto) {
        if(!registerDto.password().equals(registerDto.rePassword())) {
            return "redirect:/register?error=true";
        }
        User user = new User();
        user.setUsername(registerDto.username());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setRole(Role.USER);

        userService.save(user);

        return "login";
    }
}
