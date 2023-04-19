package com.sparta.springlv1.repository;

import com.sparta.springlv1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
