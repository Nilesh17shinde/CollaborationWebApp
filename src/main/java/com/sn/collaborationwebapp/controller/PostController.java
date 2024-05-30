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
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        Post savedPost = postService.createPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/getPostById/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post, @PathVariable Long id) {
        Post updatedPost = postService.updatePost(post, id);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post with Id " + id + " deleted successfully");
    }

    @GetMapping("/PostTitle/{title}")
    public ResponseEntity<Post> getPostByTitle(@PathVariable String title) {
        Post post = postService.findByPostTitle(title);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/getPostByStatus/{status}")
    public ResponseEntity<List<Post>> getPostByStatus(@PathVariable String status) {
        List<Post> posts = postService.findByPostStatus(status);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/getPostByTitleStartingWith/{letter}")
    public ResponseEntity<List<Post>> getPostByTitleStartingWith(@PathVariable String letter) {
        List<Post> posts = postService.findByPostTitleStartingWith(letter);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
