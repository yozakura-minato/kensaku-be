package com.yozakura_minato.kensaku_be.repository;

import com.yozakura_minato.kensaku_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
