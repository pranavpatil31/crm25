package com.crm.controller;

import com.crm.entity.Comment;
import com.crm.entity.Post;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    // we are using dependency injection fromm above code by adding constructor parametrs in below code
    public CommentController(PostRepository postRepository, CommentRepository commentRep) {
        this.postRepository = postRepository;
        this.commentRepository = commentRep;
    }
    @PostMapping
    public String createComment(
            @RequestBody Comment comment,
            @RequestParam long postId //for thisto save below code i written get method and same for comment also
            ){
            Post post = postRepository.findById(postId).get();
            comment.setPost(post);//setting up FK   and has address of post and then
            commentRepository.save(comment);//automatically FK id will go to db(just set the post obj address while)
            return "Comment created successfully";//saving the comment);it will automaticallydo the 1 line comment

    }
}
