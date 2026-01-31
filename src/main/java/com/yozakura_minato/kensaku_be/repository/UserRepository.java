package com.yozakura_minato.kensaku_be.repository;

import com.yozakura_minato.kensaku_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for users
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Method to find user from email
     * @param email string
     * @return User entity
     */
    User findByEmail(String email);

}
