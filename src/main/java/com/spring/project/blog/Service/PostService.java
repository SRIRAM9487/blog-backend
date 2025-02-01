package com.spring.project.blog.Service;

import com.spring.project.blog.Model.PostModel;
import com.spring.project.blog.Repository.PostRepository;
import com.spring.project.blog.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final Logger LOG = LoggerFactory.getLogger(PostService.class);


    @Autowired
    private UserRepository userrepo;

    @Autowired
    private PostRepository postrepo;

    public void idCheker(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The id cannot be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("The id cannot be negative");
        }
    }

    public List<PostModel> showAllPost() {
        try {
            List<PostModel> posts = postrepo.findAll();
            LOG.info("Post data has been fetched");
            return posts;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public List<PostModel> showPostByUserId(Long id) {
        try {
            List<PostModel> posts = postrepo.findByUserId(id);
            LOG.info("Post data has been fetched");
            return posts;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public PostModel addPostByUserId(Long id, String content) {

        try {
            PostModel post = PostModel.builder().user(userrepo.findById(id).orElseThrow(() -> {
                LOG.error("user not found");
                throw new IllegalArgumentException("user not found");
            })).content(content).build();
            LOG.info("The article posted");
            return postrepo.save(post);
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public PostModel updatePostByUserId(Long postId, String content) {
        try {
            PostModel post = postrepo.findById(postId).orElseThrow(() -> {
                LOG.error("failed to udpate post with id : " + postId);
                throw new IllegalArgumentException("post cannot be updated");
            });
            post.setContent(content);
            LOG.info("User updated successfully");
            return postrepo.save(post);
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    @Transactional
    public Object deletePostByUserId(Long userId, Long postId) {
        try {
            if (userrepo.existsById(userId)) {
                throw new IllegalStateException("user not found");
            }
            PostModel post = postrepo.findById(postId).orElseThrow(() -> {
                LOG.error("the post with id " + postId + " not found");
                throw new IllegalArgumentException("post cannot be deleted");
            });
            postrepo.deleteById(postId);
            LOG.info("post deleted successfully");
            return post;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    @Transactional
    public List<PostModel> deleteAllPostByUserId(Long userId) {
        try {
            if (!userrepo.existsById(userId)) {
                throw new IllegalStateException("user not found");
            }
            List<PostModel> posts = postrepo.findByUserId(userId);
            LOG.info("post by user ID : " + userId + " has been deleted");
            postrepo.deleteByUserId(userId);
            return posts;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }
}
