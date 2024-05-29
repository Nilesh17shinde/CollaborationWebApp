package com.sn.collaborationwebapp.serviceImpl;

import com.sn.collaborationwebapp.entity.Post;
import com.sn.collaborationwebapp.exception.ResourceNotFoundException;
import com.sn.collaborationwebapp.repositories.PostRepo;
import com.sn.collaborationwebapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Override
    public Post createPost(Post post) {
        post.setPostReleaseDate(new Date());
        return postRepo.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    @Override
    public Post updatePost(Post post, Long id) {
        Post existingPost = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        existingPost.setPostDescription(post.getPostDescription());
        existingPost.setPostTitle(post.getPostTitle());
        existingPost.setPostStatus(post.getPostStatus());
        existingPost.setPostType(post.getPostType());
        existingPost.setFutureDate(post.getFutureDate());
        existingPost.setUpdatedDate(new Date());
        return postRepo.save(existingPost);
    }

    @Override
    public void deletePost(Long id) {
        Post existingPost = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepo.delete(existingPost);
    }
}
