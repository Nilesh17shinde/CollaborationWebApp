package com.sn.collaborationwebapp.repositories;

import com.sn.collaborationwebapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {
    Optional<Post> findByPostTitle(String title);
    List<Post> findByPostStatus(String status);
    List<Post> findByPostTitleStartingWith(String letter);
}
