package com.poc.danabijak.minilending.service;

import com.poc.danabijak.minilending.config.LoanConfig;
import com.poc.danabijak.minilending.exception.NoCustomerFoundException;
import com.poc.danabijak.minilending.repository.CustomerRepository;
import com.poc.danabijak.minilending.repository.LoanRepository;
import com.poc.danabijak.minilending.repository.entity.Customer;
import com.poc.danabijak.minilending.repository.entity.Loan;
import com.poc.danabijak.minilending.repository.entity.TermOfPayment;
import com.poc.danabijak.minilending.service.response.ApproveLoanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanApplicationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanConfig loanConfig;

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getLoan(String citizenID) throws Exception{
        Customer customer = customerRepository.findByCitizenID(citizenID);
        if(customer == null){
            throw new NoCustomerFoundException("customer not found");
        }
        return loanRepository.findAllByCustomerID(customer.getCustomerID());
    }

    public ApproveLoanResponse applyingLoan(String customerName,
                                            String customerAge,
                                            String customerAddress,
                                            String customerOccupation,
                                            String applicationID,
                                            String citizenID,
                                            String loanAmount,
                                            String term) throws Exception{
        ApproveLoanResponse response = new ApproveLoanResponse();

        Customer customerResult = customerRepository.findByCitizenID(citizenID);
        if(customerResult == null) {
            Customer customer = new Customer();
            customer.setName(customerName);
            customer.setAddress(customerAddress);
            customer.setAge(Integer.valueOf(customerAge));
            customer.setOccupation(customerOccupation);
            customer.setCitizenID(citizenID);
            customerResult = customerRepository.save(customer);
        }

        Loan loan = new Loan();
        loan.setCustomerID(customerResult.getCustomerID());
        loan.setApplicationID(applicationID);
        loan.setApplicationFee(new BigDecimal(loanConfig.getApplicationFee()));
        loan.setInterest(12.5);
        loan.setLoanAmount(new BigDecimal(loanAmount));

        BigDecimal installment = loan.getLoanAmount()
                .multiply(new BigDecimal(100+loan.getInterest()))
                .divide(new BigDecimal(100))
                .divide(new BigDecimal(term));

        List<TermOfPayment> termOfPayments = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(term); i++) {
            TermOfPayment termOfPayment = new TermOfPayment();
            termOfPayment.setId(Long.valueOf(i));
            termOfPayment.setAdminFee(installment
                .multiply(new BigDecimal(loanConfig.getAdminFeePercentage()))
                .divide(new BigDecimal(100)));
            termOfPayment.setAmount(installment);
            termOfPayment.setPaymentStatus("not paid");
            termOfPayments.add(termOfPayment);
        }

        loan.setTermOfPayments(termOfPayments);
        Loan loanResult = loanRepository.save(loan);

        response.setCustomer(customerResult);
        response.setLoan(loanResult);

        return response;
    }
}
