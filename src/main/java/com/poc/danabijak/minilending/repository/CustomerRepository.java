package com.poc.danabijak.minilending.repository;

import com.poc.danabijak.minilending.repository.entity.Customer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends CrudRepository <Customer,Long> {
    Customer findByCitizenID(String citizenID);
}
