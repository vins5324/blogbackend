package com.myblog.service.impl;                                                   // 6th Step

import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// (PostDto)  is a POJO Class there is a class with the properties and where we interacting this properties by a getter and setter
    // it's a ordinary java BEAN (And it cannot Mapped) but Entity Class Mapped..that's why we convert POJO class to Entity Class
    // to mapped data into DataBase..
    @Service  //This Annotation is use to tell that this is the service layer
    public class PostServiceImpl implements PostService {


     private PostRepository postRepository;                                         //3rd Step
    private ModelMapper mapper; //71st Step
        public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
            this.postRepository = postRepository;
            this.mapper = mapper;
        }
     //To Save the DATA into  database we use POSTREPOSITORY
    // Q:-What are the ways we performed Dependancy Injection..?
    // Ans:-there are two ways to inject the BEAN: 1st @AutoWired.. 2nd Constructor Based Injection..


    @Override                                                                        //1st Step
    public PostDto createPost (PostDto postDto) {
         Post post = mapToEntity(postDto);
         Post newPost = postRepository.save(post);                                   //4th Step
         PostDto newPostDto = mapToDto(newPost);                                     //5th Step
         return newPostDto;
    }



    @Override                                                                            //9th STEP
       // public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {   // 19th STEP (argument) //26th STEP (String sortBy) //28th Step (argument)
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {   //35th STEP

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?         //29th STEP
            Sort.by(sortBy).ascending():
                   Sort.by(sortBy).descending();

            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);                  //20th STEP
            Page<Post> posts = postRepository.findAll(pageable);                   //21st STEP
            List<Post> contents = posts.getContent();                              // 22nd STEP CONVERTED TO LIST

           List<PostDto> postDtos = contents.stream().map(post -> mapToDto(post)).collect(Collectors.toList()); //32ND STEP

           PostResponse postResponse = new PostResponse();                            //31st STEP

           postResponse.setContent(postDtos);                                        //33rd STEP
           postResponse.setPageNo(posts.getNumber());
           postResponse.setPageSize(posts.getSize());
           postResponse.setTotalElements(posts.getTotalElements());
           postResponse.setTotalPages(posts.getTotalPages());
           postResponse.setLast(posts.isLast());

           return postResponse;                                                     //34th STEP

        //return contents.stream().map(post -> mapToDto(post)).collect(Collectors.toList()); //23RD STEP
            // List<Post> posts = postRepository.findAll();
            //Convert to Dto
            //Stream API Java 8 feature and Lambdas Expression
            //return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        }

    @Override
    public PostDto getPostById(long id) {                                         //11th STEP  if else condition /Exception Handling
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id));

        return mapToDto(post);
    }

    @Override                                                                     //14th STEP
    public PostDto updatePost(PostDto postDto, long id) {
            //Get post by id from the database
        //Converting DTO to Entity
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        //Converting Entity to DTO
         return mapToDto(updatedPost);
        }

    @Override                                                                        //16th STEP
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }


    //Converting PostDto into Entity                                                  //2nd Step
    Post mapToEntity(PostDto postDto ){
        Post post = mapper.map(postDto, Post.class);                                 //72nd STEP
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }


    //Converting Entity into PostDto
    PostDto mapToDto(Post post){                                                     //6th Step
        PostDto postDto = mapper.map(post, PostDto.class);                           //73rd STEP
//            PostDto postDto = new PostDto();
//            postDto.setId(post.getId());
//            postDto.setTitle(post.getTitle());
//            postDto.setDescription(post.getDescription());
//            postDto.setContent(post.getContent());
            return postDto;
    }
}
