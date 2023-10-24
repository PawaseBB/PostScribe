package net.venom.springboot.mapper;

import net.venom.springboot.dto.PostDto;
import net.venom.springboot.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {
	// Map Post entity to PostDto
	
	public static PostDto maptoPostDto(Post post) {
		
		return PostDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.url(post.getUrl())
				.content(post.getContent())
				.shortDescription(post.getShortDescription())
				.createdOn(post.getCreatedOn())
				.updatedOn(post.getUpdatedOn())
				.comments(post.getComments().stream()
						.map(CommentMapper::maptoCommentDto)
						.collect(Collectors.toSet()))
				.build();
	}

	//Map PostDto to Post entity

	public static Post maptoPost(PostDto postDto){
		return Post.builder()
				.id(postDto.getId())
				.title(postDto.getTitle())
				.url(postDto.getUrl())
				.content(postDto.getContent())
				.shortDescription(postDto.getShortDescription())
				.createdOn(postDto.getCreatedOn())
				.updatedOn(postDto.getUpdatedOn())
				.build();
	}
}
