package com.spring.project.blog.Repository;

import com.spring.project.blog.Model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {
    List<PostModel> findByUserId(Long id);

    void deleteByUserId(Long userId);
}
