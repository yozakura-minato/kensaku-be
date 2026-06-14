package com.yozakura_minato.kensaku_be.repository;

import com.yozakura_minato.kensaku_be.entity.RedisToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
    Optional<RedisToken> findAllById(String jwtId);
}
