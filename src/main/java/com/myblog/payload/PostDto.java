package com.myblog.payload; //4th Step

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data //instant of getter and setter we use @data annotation
public class PostDto {
    private long id;

    @NotEmpty(message = "you will to write a message")//means title should be not empty                                 //79th STEP(Annotation)
    @Size(min = 2, message = "post tittle should be atleast 2 characters")
    private String title;

    @NotEmpty                                                                   //80th STEP
    @Size(min = 1, message = "post description should be atleast 1 characters")
    private String description;

    @NotEmpty                                                                   //81st STEP
    @Size(min = 5, message = "post content should be atleast 5 characters")
    private String content;
}
