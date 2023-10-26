package com.example.ApiSpringBoot.repository;

import com.example.ApiSpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRespository extends JpaRepository<User,Integer> {
  @Query("SELECT u FROM User u WHERE u.username = :username")
  User getByUsername(@Param("username") String username);
}
