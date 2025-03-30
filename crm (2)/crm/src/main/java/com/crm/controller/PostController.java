package com.crm.controller;

import com.crm.entity.Post;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostRepository postRepository;
    private CommentRepository commentRep;
// we are using dependency injection fromm above code by adding constructor parametrs in below code
    public PostController(PostRepository postRepository, CommentRepository commentRep) {
        this.postRepository = postRepository;
        this.commentRep = commentRep;
    }
    @PostMapping
    public String createPost(
            @RequestBody Post post
            ){
        System.out.println("save");

        postRepository.save(post);
        return  null;
    }
    @DeleteMapping
    public  void deletePost(){
        postRepository.deleteById(1L);
    }
}
