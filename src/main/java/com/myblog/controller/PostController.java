package com.myblog.controller; //7th Step

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
     private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts create blog post rest api
    @PreAuthorize("hasRole('ADMIN')")//89Th STEP
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){//@RequestBody take the content from JASON object nd put in postDto java object

        if (result.hasErrors()){//82nd STEP
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201
    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=5 create get all post api & in pagination form
    //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title  sorting


    //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=asc
    // get all posts rest API
    @PreAuthorize("hasRole('ADMIN','USER')")
    @GetMapping
    //public List<PostDto> getAllPosts                                                                //8th Step for all post
    public PostResponse getAllPosts                                                                   //37TH STEP
    (@RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,                  //17th STEP(argument)
     @RequestParam(value="pageSize", defaultValue = "10", required = false) int pageSize,             //17th Step
     @RequestParam(value = "sortBy", defaultValue = "id", required = false)String sortBy,              //24th Step
     @RequestParam(value = "sortDir", defaultValue = "asc", required = false)String sortDir){             //27th STEP

        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }


    //http://localhost:8080/api/posts/1  create get post api
    @PreAuthorize("hasRole('ADMIN','USER')")
    @GetMapping("/{id}") //Get all list
    public ResponseEntity<PostDto> getPostById(@PathVariable("id")long id){                         //10th STEP get post by id number
        PostDto dto = postService.getPostById(id);
        return ResponseEntity.ok(dto);                                                               //12th Step
    }

    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")//90th STEP
    @PutMapping("/{id}") //To Update data                                                                            //13th STEP
    public ResponseEntity <PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id")long id){
        PostDto dto = postService.updatePost(postDto, id);
        return ResponseEntity.ok(dto);
    }


    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")                                                   //91st STEP(Annotation)
    @DeleteMapping("/{id}")                                                           //15th STEP
    public ResponseEntity<String> deletePostById(@PathVariable("id")long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Your post is Deleted!", HttpStatus.OK);
    }
}
