package com.poc.danabijak.minilending.service.response;

import com.poc.danabijak.minilending.repository.entity.Customer;
import com.poc.danabijak.minilending.repository.entity.Loan;
import lombok.Data;

@Data
public class ApproveLoanResponse {
    private Customer customer;
    private Loan loan;
}
