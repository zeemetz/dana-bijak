package com.poc.minilending.controller.response;

import com.poc.minilending.repository.entity.Loan;
import lombok.Data;

import java.util.List;

@Data
public class ListLoanResponse {
    private List<Loan> loans;
}
