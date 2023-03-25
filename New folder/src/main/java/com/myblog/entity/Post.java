
package com.myblog.entity; //1st Step

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data //do not write getter and setter it's lombok annotation for automatically created getter setter
@AllArgsConstructor //for constructor with argument
@NoArgsConstructor //for default constructor
@Entity //table name
@Table(name = "posts" , uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {

    @Id //for auto generated id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)  //39TH STEP
    //CascadeAll means you change in the table one those change should be seen in table 2

    private Set<Comment> comments = new HashSet<>();
}
