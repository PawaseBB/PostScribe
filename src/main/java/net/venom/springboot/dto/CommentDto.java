package net.venom.springboot.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private  Long id;
    @NotEmpty(message = "Name should not be empty or null")
    private  String name;
    @NotEmpty(message = "Email should not be empty or null")
    @Email
    private String email;
    @NotEmpty(message = "Message body should not be empty or null")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
