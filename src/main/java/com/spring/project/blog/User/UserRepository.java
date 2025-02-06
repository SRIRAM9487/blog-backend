package com.spring.project.blog.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(String Email);

    boolean existsByEmail(String email);

    UserModel findByUsername(String username);
}
