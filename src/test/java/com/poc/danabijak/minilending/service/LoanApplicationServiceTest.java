package com.poc.minilending.service;

import com.poc.minilending.config.LoanConfig;
import com.poc.minilending.exception.NoCustomerFoundException;
import com.poc.minilending.repository.CustomerRepository;
import com.poc.minilending.repository.LoanRepository;
import com.poc.minilending.repository.entity.Customer;
import com.poc.minilending.repository.entity.Loan;
import com.poc.minilending.service.response.ApproveLoanResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceTest {

    @InjectMocks
    LoanApplicationService loanApplicationService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    LoanRepository loanRepository;

    @Mock
    LoanConfig loanConfig;

    @Before
    public void setup(){
        when(loanConfig.getAdminFeePercentage()).thenReturn("1");
        when(loanConfig.getApplicationFee()).thenReturn("0");
    }

    @Test
    public void applyingLoan_existingCustomer_shouldBeSuccess() throws Exception{
        String customerName = "yoedi hariadi kurniawan";
        String customerAge = "26";
        String customerAddress = "Bangkok, thailand";
        String customerOccupation = "engineer";
        String applicationID = "0987654321";
        String citizenID = "1234567890";
        String loanAmount = "1000";
        String term = "12";

        when(customerRepository.findByCitizenID(anyString())).thenReturn(new Customer());
        when(loanRepository.save(any())).thenReturn(new Loan());
        ApproveLoanResponse response = loanApplicationService.applyingLoan(customerName, customerAge,customerAddress,customerOccupation,applicationID,citizenID,loanAmount,term);
        Assert.assertNotNull(response.getCustomer());
        Assert.assertNotNull(response.getLoan());
        verify(customerRepository,times(0)).save(any());
        verify(loanRepository,times(1)).save(any());
    }

    @Test
    public void applyingLoan_newCustomer_shouldBeSuccess() throws Exception{
        String customerName = "yoedi hariadi kurniawan";
        String customerAge = "26";
        String customerAddress = "Bangkok, thailand";
        String customerOccupation = "engineer";
        String applicationID = "0987654321";
        String citizenID = "1234567890";
        String loanAmount = "1000";
        String term = "12";

        when(customerRepository.findByCitizenID(anyString())).thenReturn(null);
        when(loanRepository.save(any())).thenReturn(new Loan());
        when(customerRepository.save(any())).thenReturn(new Customer());
        ApproveLoanResponse response = loanApplicationService.applyingLoan(customerName, customerAge,customerAddress,customerOccupation,applicationID,citizenID,loanAmount,term);
        Assert.assertNotNull(response.getCustomer());
        Assert.assertNotNull(response.getLoan());
        verify(customerRepository,times(1)).save(any());
        verify(loanRepository,times(1)).save(any());
    }

    @Test
    public void getLoan_success() throws Exception{
        List<Loan> expResult = new ArrayList<>();
        expResult.add(new Loan());

        when(loanRepository.findAllByCustomerID(any())).thenReturn(expResult);
        when(customerRepository.findByCitizenID(anyString())).thenReturn(new Customer());
        List<Loan> result = loanApplicationService.getLoan("0123456789");

        Assert.assertNotEquals(0,result.size());
    }

    @Test(expected = NoCustomerFoundException.class)
    public void getLoan_customerNotFound_shouldThrowException() throws Exception{
        List<Loan> expResult = new ArrayList<>();
        expResult.add(new Loan());

        when(customerRepository.findByCitizenID(anyString())).thenReturn(null);
        List<Loan> result = loanApplicationService.getLoan("0123456789");
    }
}
