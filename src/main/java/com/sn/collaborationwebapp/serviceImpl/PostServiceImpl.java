package com.sn.collaborationwebapp.serviceImpl;

import com.sn.collaborationwebapp.entity.Post;
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
        post.setPostDate(new Date());
        return postRepo.save(post);
    }

    @Override
    public List<Post> getAllPost()  {
        return postRepo.findAll();
    }
}
