package net.venom.springboot.service.impl;

import net.venom.springboot.dto.CommentDto;
import net.venom.springboot.entity.Comment;
import net.venom.springboot.entity.Post;
import net.venom.springboot.entity.User;
import net.venom.springboot.mapper.CommentMapper;
import net.venom.springboot.repository.CommentRepository;
import net.venom.springboot.repository.PostRepository;
import net.venom.springboot.repository.UserRepository;
import net.venom.springboot.service.CommentService;
import net.venom.springboot.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.maptoComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentMapper::maptoCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream()
                .map((comment)-> CommentMapper.maptoCommentDto(comment))
                .collect(Collectors.toList());
    }
}
