package com.poc.danabijak.minilending.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;
import java.util.List;

@Data
@RedisHash("loan")
public class Loan {
    @Id
    private Long loanID;
    @Indexed
    private Long customerID;

    private String applicationID;
    private BigDecimal loanAmount;
    private BigDecimal applicationFee;
    private double interest;
    private String issueDate;
    private List<TermOfPayment> termOfPayments;
}
