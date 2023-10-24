package net.venom.springboot.dto;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Long id;
	@NotEmpty(message = "Post title should not be empty")
	private String title;
	private String url;
	@NotEmpty(message = "Post content should not be empty")
	private String content;
	@NotEmpty(message = "Post shortDescription should not be empty")
	private String shortDescription;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private Set<CommentDto> comments;
	
}
