package net.venom.springboot.service.impl;

import net.venom.springboot.dto.PostDto;
import net.venom.springboot.entity.Post;
import net.venom.springboot.entity.User;
import net.venom.springboot.mapper.PostMapper;
import net.venom.springboot.repository.PostRepository;
import net.venom.springboot.repository.UserRepository;
import net.venom.springboot.service.PostService;
import net.venom.springboot.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDto> findAllPost() {
      List<Post>  posts= postRepository.findAll();
      return posts.stream().map(PostMapper::maptoPostDto)
              .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Post> posts =  postRepository.findPostByUser(userId);
        return posts.stream()
                .map((post) -> PostMapper.maptoPostDto(post))
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post =PostMapper.maptoPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post=postRepository.findById(postId).get();
        return PostMapper.maptoPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Post post = PostMapper.maptoPost(postDto);
        post.setCreatedBy(createdBy);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post=postRepository.findByUrl(postUrl).get();
        return PostMapper.maptoPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> post= postRepository.searchPosts(query);
        return post.stream()
                .map(PostMapper::maptoPostDto)
                .collect(Collectors.toList());
    }
}
