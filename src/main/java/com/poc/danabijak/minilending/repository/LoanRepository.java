package com.poc.danabijak.minilending.repository;

import com.poc.danabijak.minilending.repository.entity.Customer;
import com.poc.danabijak.minilending.repository.entity.Loan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository <Loan,Long> {
    List<Loan> findAllByCustomerID(Long customerID);
}
