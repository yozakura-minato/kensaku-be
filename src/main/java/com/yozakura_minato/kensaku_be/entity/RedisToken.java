package com.yozakura_minato.kensaku_be.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

/**
 * <p>Attributes:</p>
 * <ul>
 *     <li>{@code jwtId}: String</li>
 *     <li>{@code expirationTime}: long</li>
 * </ul>
 */
@Setter
@Getter
@Builder
@RedisHash("RedisHash")
public class RedisToken {

    @Id
    private String jwtId;

    @TimeToLive(unit = TimeUnit.DAYS)
    private long expirationTime;

}
