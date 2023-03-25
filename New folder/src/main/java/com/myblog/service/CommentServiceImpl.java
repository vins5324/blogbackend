package com.myblog.service;//   42nd STEP

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.BlogAPIException;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private PostRepository postRepository;//45th Step
    private CommentRepository commentRepository;//48th Step
    private ModelMapper mapper; //74th STEP

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto); //43rd Step

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));//46th Step

        comment.setPost(post);

        //Entity to DTO to save comment
        Comment newComment = commentRepository.save(comment);//49th Step

        CommentDto dto =  mapToDto(newComment);//50th Step



        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {//55th Step

        postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("post", "id", postId));//58th Step

        List<Comment> comments = commentRepository.findByPostId(postId);//55Th Step
        return comments.stream().map(comment ->
                mapToDto(comment)).collect(Collectors.toList());//55th Step

    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {//60th STEP

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId));

        if (comment.getPost().getId()!=post.getId()){//63rd STEP
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDto(comment);//converting comment into mapToDto//60TH STEP
    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {//66TH STEP
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id));

        if (comment.getPost().getId()!=post.getId()){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post not matching with  comment");
        }

        comment.setId(id);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);//converting newComment into maptoDto
    }

    @Override
    public void deleteComment(long postId, long id) {//70th STEP
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId));

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id));

        if (comment.getPost().getId()!=post.getId()){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post not matching with  comment");}


            commentRepository.deleteById(id);
    }

    //Entity to DTO
    private CommentDto mapToDto(Comment newComment) { //51st Step
        CommentDto commentDto = mapper.map(newComment, CommentDto.class);//75th STEP
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(newComment.getId());
//        commentDto.setName(newComment.getName());
//        commentDto.setEmail(newComment.getEmail());
//        commentDto.setBody(newComment.getBody());
//        commentDto.setPost(newComment.getPost());

        return commentDto;
    }

    // DTO to Entity
    private Comment mapToEntity(CommentDto commentDto) {//44th Step
        Comment comment = mapper.map(commentDto, Comment.class);//76th STEP
//        Comment comment = new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());

        return comment;
    }
}
