package com.myblog.service;  //5th Step

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    //List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);          //18th Step (argumnet) //25th Step (String sortBy)
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);//36th STEP

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
