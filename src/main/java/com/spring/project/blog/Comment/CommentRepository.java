package com.spring.project.blog.Comment;

import com.spring.project.blog.Post.PostModel;
import com.spring.project.blog.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel,Long> {
    List<CommentModel> findByParentCommentId(Long id);

    List<CommentModel> findByPostId(Long id);

    List<CommentModel> findByUserId(Long id);

    void deleteByParentCommentId(CommentModel parrentComment);

    void deleteByPost(PostModel post);

    void deleteByUser(UserModel user);
}
