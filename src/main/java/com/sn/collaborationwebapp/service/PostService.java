package com.sn.collaborationwebapp.service;

import com.sn.collaborationwebapp.entity.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post updatePost(Post post, Long id);
    void deletePost(Long id);
    Post findByPostTitle(String title);
    List<Post> findByPostStatus(String status);
    List<Post> findByPostTitleStartingWith(String letter);
}
