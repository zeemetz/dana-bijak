package com.poc.minilending.service.response;

import com.poc.minilending.repository.entity.Customer;
import com.poc.minilending.repository.entity.Loan;
import lombok.Data;

@Data
public class ApproveLoanResponse {
    private Customer customer;
    private Loan loan;
}
