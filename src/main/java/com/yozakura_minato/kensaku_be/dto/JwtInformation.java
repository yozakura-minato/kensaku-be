package com.yozakura_minato.kensaku_be.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Builder
public class JwtInformation implements Serializable {

    private String jwtId;
    private Date issueTime;
    private Date expirationTime;

}
