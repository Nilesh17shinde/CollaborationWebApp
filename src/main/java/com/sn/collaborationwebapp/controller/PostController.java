package com.sn.collaborationwebapp.controller;

import com.sn.collaborationwebapp.entity.Post;
import com.sn.collaborationwebapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        Post savedPost = postService.createPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> posts = postService.getAllPost();
        return ResponseEntity.ok(posts);
    }

}