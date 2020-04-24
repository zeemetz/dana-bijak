package com.poc.danabijak.minilending.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;

@Data
public class TermOfPayment {

    @Indexed
    private Long id;

    private String dueDate;
    private BigDecimal amount;
    private BigDecimal adminFee;
    private String paymentStatus;
}
