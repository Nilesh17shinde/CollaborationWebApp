package com.sn.collaborationwebapp.service;

import com.sn.collaborationwebapp.entity.Post;

import java.util.List;

public interface PostService {
Post createPost(Post post);
List<Post>getAllPost();
Post getPostById(Long id);
Post updatePost(Post post, Long id);
void deletePost(Long id);
}
