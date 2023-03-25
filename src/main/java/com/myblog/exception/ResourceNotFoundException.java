package com.myblog.exception;  //8th Step

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)// to convert an exception to an Http response status
public class ResourceNotFoundException extends RuntimeException {                      //1st Step

    private String resourceName;                                                       // 2nd Step
    private String fieldName;
    private long fieldValue;


    //New ResourceNotFoundException("post", "id", 1)
    public ResourceNotFoundException (String resourceName, String fieldName, long fieldValue){     //3rd Step
        //Exception Handling
        super(String.format("%s  not found with  %s :'%s' ", resourceName, fieldName, fieldValue)); //5th Step

        this.resourceName = resourceName;                                                         //4th Step
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {                                                  //6th Step
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
