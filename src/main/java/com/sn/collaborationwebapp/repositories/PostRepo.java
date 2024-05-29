package com.sn.collaborationwebapp.repositories;

import com.sn.collaborationwebapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
