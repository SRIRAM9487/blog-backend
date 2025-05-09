package com.spring.project.blog.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.project.blog.Comment.CommentModel;
import com.spring.project.blog.User.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;


    @CreationTimestamp
    @Column(name = "createdAt",nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserModel user;

   @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<CommentModel> comments;
}
