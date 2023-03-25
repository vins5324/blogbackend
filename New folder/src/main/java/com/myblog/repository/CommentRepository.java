package com.myblog.repository;//47th Step

import com.myblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long postId);//53rd Step
}
