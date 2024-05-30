package com.sn.collaborationwebapp.controller;

import com.sn.collaborationwebapp.entity.Admin;
import com.sn.collaborationwebapp.entity.Post;
import com.sn.collaborationwebapp.payloads.ApiResponse;
import com.sn.collaborationwebapp.service.AdminService;
import com.sn.collaborationwebapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    PostService postService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createAdmin(@Valid @RequestBody Admin admin) {
        System.out.println("Received Content-Type: application/json");
        Admin savedAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<>(new ApiResponse("Admin created successfully", true), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(this.adminService.getAllAdmin());
    }

    //    Post Accessibility
    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    @DeleteMapping("/deletePostById/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post with Id " + id + " deleted successfully");
    }
}
