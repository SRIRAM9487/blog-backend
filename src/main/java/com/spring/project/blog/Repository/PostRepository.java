package com.spring.project.blog.Repository;

import com.spring.project.blog.Model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostModel,Long> {
}
