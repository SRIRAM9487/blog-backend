package com.spring.project.blog.Service;

import com.spring.project.blog.Model.CommentModel;
import com.spring.project.blog.Model.PostModel;
import com.spring.project.blog.Model.UserModel;
import com.spring.project.blog.Repository.CommentRepository;
import com.spring.project.blog.Repository.PostRepository;
import com.spring.project.blog.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private UserRepository userrepo;

    @Autowired
    private PostRepository postrepo;

    @Autowired
    private CommentRepository commentrepo;

    public void idCheker(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The id cannot be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("The id cannot be negative");
        }
    }

    public List<CommentModel> showAllComments() {
        try {
            List<CommentModel> comments = commentrepo.findAll();
            LOG.info("Comments has been fetched");
            return comments;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public CommentModel showCommentById(Long id) {
        try {
            CommentModel comment = commentrepo.findById(id).orElseThrow(() -> {
                LOG.error("The comment not found");
                throw new IllegalStateException("Comment not found");
            });
            LOG.info("Comment with Id : " + id + " has been fetched");
            return comment;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public List<CommentModel> showRepliesById(Long parentId) {
        try {

            CommentModel parentComment = commentrepo.findById(parentId).orElseThrow(() -> {
                LOG.error("Comment not found");
                throw new IllegalStateException("Comment not found");
            });
            List<CommentModel> comments = commentrepo.findByParentCommentId(parentId);
            LOG.info("replies of comment with ID : " + parentId + " has been fetched");
            if (comments.isEmpty()) {
                LOG.info("There are no replies");
            }
            return comments;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public List<CommentModel> showRepliesByPost(Long postid) {
        try {
            PostModel post = postrepo.findById(postid).orElseThrow(() -> {
                LOG.error("Post not found");
                throw new IllegalStateException("Post not found");
            });
            List<CommentModel> comments = commentrepo.findByPostId(postid);
            if (comments.isEmpty()) {
                LOG.info("There are no Comments");
            }
            LOG.info("The comment of post Id : " + postid + " has been fetched");
            return comments;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public List<CommentModel> showCommentsByUserId(Long userId) {
        try {
            UserModel user = userrepo.findById(userId).orElseThrow(() -> {
                LOG.error("User not found");
                throw new IllegalStateException("User not found");
            });
            List<CommentModel> comments = commentrepo.findByUserId(userId);
            if (comments.isEmpty()) {
                LOG.info("There are no Comments");
            }
            LOG.info("The comment of user Id : " + userId + " has been fetched");
            return comments;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }


    public CommentModel addCommentByPost(Long userId, Long postId, String content) {
        try {
            UserModel user = userrepo.findById(userId).orElseThrow(() -> {
                LOG.error("User not found");
                throw new IllegalStateException("User not found");
            });
            PostModel post = postrepo.findById(postId).orElseThrow(() -> {
                LOG.error("Post not found");
                throw new IllegalStateException("Post not found");
            });
            if (content.trim().isBlank()) {
                throw new IllegalArgumentException("Content cannot be empty");
            }
            CommentModel comment = CommentModel
                    .builder()
                    .content(content)
                    .post(post)
                    .user(user)
                    .build();
            commentrepo.save(comment);
            LOG.info("Comment added");
            return comment;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public CommentModel addCommentByParentComment(Long userId, Long parentId, String content) {
        try {
            UserModel user = userrepo.findById(userId).orElseThrow(() -> {
                LOG.error("User not found");
                throw new IllegalStateException("User not found");
            });
            CommentModel parentComment = commentrepo.findById(parentId).orElseThrow(() -> {
                LOG.error("Comment not found");
                throw new IllegalStateException("Comment not found");
            });
            if (content.trim().isBlank()) {
                throw new IllegalArgumentException("Content cannot be empty");
            }
            CommentModel comment = CommentModel
                    .builder()
                    .parentComment(parentComment)
                    .user(user)
                    .content(content)
                    .build();
            commentrepo.save(comment);
            LOG.info("Comment added");
            return comment;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public CommentModel updateComment(Long id, String content) {
        try {
            idCheker(id);
            CommentModel existing_comment = commentrepo.findById(id).orElseThrow(() -> {
                LOG.error("Comment not found");
                throw new IllegalArgumentException("Comment not found");
            });
            if (content.trim().isBlank()) {
                throw new IllegalArgumentException("Content cannot be empty");
            }
            existing_comment.setContent(content);
            commentrepo.save(existing_comment);
            LOG.info("comment updated");
            return existing_comment;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public String deleteCommentById(Long id) {
        try {
            idCheker(id);
            if(!commentrepo.existsById(id)){
                throw new IllegalStateException("Comment not found");
            }
           commentrepo.deleteById(id);
            LOG.info("comment deleted");
            return "Comment deleted";
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public Object deleteCommentOfParentById(Long parentId) {
        try {
            idCheker(parentId);
            CommentModel parentComment = commentrepo.findById(parentId).orElseThrow(() -> {
                LOG.error("Comment not found");
                throw new IllegalStateException("Comment not found");
            });
            commentrepo.deleteByParentCommentId(parentComment);
            LOG.info("comment deleted");
            return "Comment deleted";
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public Object deleteCommentByPost(Long postId) {
        try {
            idCheker(postId);
            PostModel post = postrepo.findById(postId).orElseThrow(() -> {
                LOG.error("Post not found");
                throw new IllegalStateException("Post not found");
            });
            commentrepo.deleteByPost(post);
            LOG.info("comment deleted");
            return "Comment deleted";
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public Object deleteCommentByUserId(Long userId) {
        try {
            idCheker(userId);
            UserModel user = userrepo.findById(userId).orElseThrow(() -> {
                LOG.error("User not found");
                throw new IllegalStateException("User not found");
            });
            commentrepo.deleteByUser(user);
            LOG.info("comment deleted");
            return "Comment deleted";
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

}
