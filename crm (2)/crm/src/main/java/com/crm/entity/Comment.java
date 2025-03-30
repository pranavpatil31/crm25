package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne//First word of any annotation belongs to class here many belongs to comment and one to post
    @JoinColumn(name = "post_id")
    private Post post;
    //create this were we want to create FK  if we create in post then in db post_id will be created in db post
    // if we want in post then one to many in post
    //here we want to add post_id Fk in comment table so  write @joincolumn in comment.java it is used to define FK

}