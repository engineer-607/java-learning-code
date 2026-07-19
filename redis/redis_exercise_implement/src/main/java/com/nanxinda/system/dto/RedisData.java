package com.nanxinda.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
public class RedisData {
    private Object data;
    private LocalDateTime expireTime;
}
