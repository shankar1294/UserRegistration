package com.example.userregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userregistration.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
