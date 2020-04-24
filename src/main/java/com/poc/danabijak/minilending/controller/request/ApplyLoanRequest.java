package com.poc.minilending.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ApplyLoanRequest {
    @Pattern(regexp = "[a-z A-Z]+$",message = "customer_name_invalid")
    private String customerName;

    @Pattern(regexp = "[0-9]+$", message = "customer_age_invalid")
    @Length(max = 3, min = 1, message = "customer_age_invalid")
    @Min(value = 1, message = "customer_age_invalid")
    private String customerAge;

    @Pattern(regexp = "[a-z A-Z 0-9 , .]+$",message = "customer_address_invalid")
    private String customerAddress;

    @Pattern(regexp = "[a-z A-Z]+$",message = "customer_occupation_invalid")
    private String customerOccupation;

    @Pattern(regexp = "[0-9]+$", message = "citizenid_invalid")
    @Length(max = 10, min = 10, message = "citizenid_invalid")
    private String citizenID;

    @Pattern(regexp = "[0-9 a-z A-Z]+$", message = "applicationid_invalid")
    @Length(max = 10, min = 10, message = "applicationid_invalid")
    private String applicationID;

    @Pattern(regexp = "[0-9]+$", message = "loan_amount_invalid")
    @Min(value = 100, message = "loan_amount_invalid")
    private String loanAmount;

    @Pattern(regexp = "(12|24|36|48|60)$", message = "term_invalid")
    private String term;
}
