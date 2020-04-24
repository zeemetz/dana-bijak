package com.poc.minilending.controller.response;

import lombok.Data;

@Data
public class ApplyLoanResponse {
    private String citizenID;
    private Long customerID;
    private Long loanID;
    private String applicationID;
}
