package com.myblog.controller;//52nd Step

import com.myblog.payload.CommentDto;
import com.myblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Object> createComment
            (@PathVariable(value = "postId")long postId,
             @Valid                                                      //83rd Step (Annotation)
             @RequestBody CommentDto commentDto, BindingResult result){

        if (result.hasErrors()){                               //84th STEP
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CommentDto dto = commentService.createComment(postId, commentDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")//56th Step
    public List<CommentDto> getCommentByPostId(
            @PathVariable(value = "postId")long postId){
       return commentService.getCommentsByPostId(postId);
    }

    //http://localhost:8080/api/posts/1/comments/1
    @GetMapping("/posts/{postId}/comments/{id}")//61st Step
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId,
                                                     @PathVariable("id")long commentId){

        CommentDto dto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1/comments/1
    @PutMapping("/posts/{postId}/comments/{id}")//64th STEP
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId,
                                                    @PathVariable("id") long id,
                                                    @RequestBody CommentDto commentDto){

        CommentDto dto = commentService.updateComment(postId, id, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);//67th Step
    }

    //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/posts/{postId}/comments/{id}")//68th STEP
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,
                                          @PathVariable("id") long id){

        commentService.deleteComment(postId, id);
        return new ResponseEntity<>("Your Comment is Deleted", HttpStatus.OK);//71st STEP
    }
}
