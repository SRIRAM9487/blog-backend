package com.spring.project.blog.Repository;

import com.spring.project.blog.Model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel,Long> {
}
