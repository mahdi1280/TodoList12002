package ir.iranianCyber.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TodoDto(
        @NotBlank(message = "todo.dto.title.blank")
        @Size(min = 3, message = "todo.dto.size.title.not.valid")
        String title,
        @NotBlank(message = "todo.dto.description.blank")
        @Size(min = 3, message = "todo.dto.size.description.not.valid")
        String description) { }
