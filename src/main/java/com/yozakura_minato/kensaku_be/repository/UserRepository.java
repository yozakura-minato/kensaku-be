package com.yozakura_minato.kensaku_be.repository;

import com.yozakura_minato.kensaku_be.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    /**
     * @param email (string)
     * @return (Users)
     */
    Users findByEmail(String email);

}
