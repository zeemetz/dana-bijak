package com.poc.minilending.repository;

import com.poc.minilending.repository.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository <Customer,Long> {
    Customer findByCitizenID(String citizenID);
}
