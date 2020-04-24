package com.poc.minilending.repository;

import com.poc.minilending.repository.entity.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository <Loan,Long> {
    List<Loan> findAllByCustomerID(Long customerID);
}
