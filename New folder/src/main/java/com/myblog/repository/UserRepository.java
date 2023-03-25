package com.myblog.repository;//94th STEP

import com.myblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {//LONG is the wrapper class name for User Entity Primary Key

        Optional<User> findByEmail(String email);
        Optional<User> findByUsernameOrEmail(String username, String email);
        Optional<User> findByUsername(String username );
        Boolean existsByUsername(String username);
        Boolean existsByEmail(String email );
}
