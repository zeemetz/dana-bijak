package com.poc.danabijak.minilending.controller.request;

import lombok.Data;

@Data
public class ApplyLoanRequest {
    private String customerName;
    private String customerAge;
    private String customerAddress;
    private String customerOccupation;
    private String citizenID;
    private String applicationID;
    private String loanAmount;
    private String term;
}
