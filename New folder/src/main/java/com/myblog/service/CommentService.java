package com.myblog.service; //41st STEP

import com.myblog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);//54th Step

    CommentDto getCommentById(long postId, long commentId);//59th STEP

    CommentDto updateComment(long postId, long id, CommentDto commentDto);//65th STEP

    void deleteComment(long postId, long id);//69th STEP
}
