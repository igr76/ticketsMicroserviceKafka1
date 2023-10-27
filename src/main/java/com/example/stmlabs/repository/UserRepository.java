package com.example.stmlabs.repository;

import com.example.stmlabs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User ,Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE login = :login ")
    Optional<User> findByLogin(String login);
}
