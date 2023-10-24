package net.venom.springboot.mapper;

import net.venom.springboot.dto.CommentDto;
import net.venom.springboot.entity.Comment;

public class CommentMapper {
    // convert comment entity to comment dto
    public static CommentDto maptoCommentDto(Comment comment){
        return  CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdateOn())
                .build();
    }

    // convert comment dto to comment entity
    public static Comment maptoComment(CommentDto commentDto){
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .content(commentDto.getContent())
                .createdOn(commentDto.getCreatedOn())
                .updateOn(commentDto.getCreatedOn())
                .build();
    }
}
