package com.spring.project.blog.Model;

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
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content", nullable = false)
    private String content = "about yourself";

    @Column(name = "email", nullable = false,unique = true)
    private String email;


    @CreationTimestamp
    @Column(name = "createdAt",nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<PostModel> posts;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<CommentModel> comments;
}
