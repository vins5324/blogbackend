package com.myblog.entity;//92nd STEP

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data//gives getter nd setters
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
                                            @UniqueConstraint(columnNames = {"email"})
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    private String username;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),//Map:-user id he USER table ki
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))//Map:- role id he ROLE table ki


    //we use this:-private Set<Role> roles; for Many to Many Mapping
    private Set<Role> roles;

}
