package com.poc.danabijak.minilending.controller.response;

import com.poc.danabijak.minilending.repository.entity.Loan;
import lombok.Data;

import java.util.List;

@Data
public class ListLoanResponse {
    private List<Loan> loans;
}
