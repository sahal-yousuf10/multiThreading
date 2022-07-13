package com.example.sahal.Springbootmultithreading.repository;

import com.example.sahal.Springbootmultithreading.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Integer> {
}
