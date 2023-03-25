package com.myblog.payload;//40th STEP


import com.myblog.entity.Post;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "give some message")//means title should be not empty                                 //83rd STEP(Annotation)
    @Size(min = 3, message = "post tittle should be atleast 3 characters")
    private String name;

    @NotEmpty
    @Email//means email should be not empty                                 //79th STEP(Annotation)
    private String email;

    @NotEmpty//means body should be not empty                                 //79th STEP(Annotation)
    @Size(min = 20, message = "post body should be atleast 20 characters")
    private String body;
   // private Post post; //57th STEP
}
