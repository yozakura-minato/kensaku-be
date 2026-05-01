package com.yozakura_minato.kensaku_be.dto.internal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class TokenPayload {

    private String token;
    private String jwtId;
    private Date expirationTime;

}
