package com.poc.minilending.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Getter
@EnableAsync
@Configuration
public class LoanConfig {
    @Value("${loan.payment.admin.fee.percentage}")
    private String adminFeePercentage;
    @Value("${loan.application.fee}")
    private String applicationFee;
}
