package com.poc.danabijak.minilending.repository.entity;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Data
@RedisHash("customer")
public class Customer {
    @Id
    private Long customerID;
    @Indexed
    @UniqueElements
    private String citizenID;

    private String name;
    private int age;
    private String address;
    private String occupation;
}
