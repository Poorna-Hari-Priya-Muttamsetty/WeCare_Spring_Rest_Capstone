package com.capstone1.repository;

import com.capstone1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.username = ?1")
    User findByUserName(String username);
}
