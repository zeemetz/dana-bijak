package com.poc.minilending.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class ListLoanRequest {
    @Pattern(regexp = "[0-9]+$", message = "citizenid_invalid")
    @Length(max = 10, min = 10, message = "citizenid_invalid")
    private String citizenID;
}
