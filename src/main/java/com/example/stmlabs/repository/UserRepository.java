package com.example.stmlabs.repository;

import com.example.stmlabs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/** Репозиторий пассажиров  */
@Repository
public interface UserRepository extends JpaRepository<User ,Long>{

    Optional<User> findByLogin(String login);


}
