package com.github.truongbb.tinytaskmanagement.repository;

import com.github.truongbb.tinytaskmanagement.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

}
