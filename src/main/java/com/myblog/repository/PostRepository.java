package com.myblog.repository; //3rd Step

import com.myblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> { //JPA Repository have pagination
}
