package net.venom.springboot.service;

import net.venom.springboot.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPost();

    List<PostDto> findPostByUser();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);

    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);
}
