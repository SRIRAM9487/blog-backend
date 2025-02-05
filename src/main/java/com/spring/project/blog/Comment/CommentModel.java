package com.spring.project.blog.Comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.project.blog.Post.PostModel;
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
@Table(name = "comment")
public class CommentModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostModel post;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<CommentModel> replies;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parentComment_id")
    private CommentModel parentComment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<CommentModel> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentModel> replies) {
        this.replies = replies;
    }

    public CommentModel getParentComment() {
        return parentComment;
    }

    public void setParentComment(CommentModel parentComment) {
        this.parentComment = parentComment;
    }
}