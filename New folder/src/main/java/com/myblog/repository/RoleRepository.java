package com.myblog.repository;//95th STEP

import com.myblog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {//LONG is the wrapper class name for Role Entity Primary Key

    Optional<Role> findByName(String name);
}
