package com.poc.danabijak.minilending.controller;

import com.poc.danabijak.minilending.controller.request.ApplyLoanRequest;
import com.poc.danabijak.minilending.controller.request.ListLoanRequest;
import com.poc.danabijak.minilending.controller.response.ApplyLoanResponse;
import com.poc.danabijak.minilending.factory.ResponseFactory;
import com.poc.danabijak.minilending.repository.entity.Customer;
import com.poc.danabijak.minilending.repository.entity.Loan;
import com.poc.danabijak.minilending.service.LoanApplicationService;
import com.poc.danabijak.minilending.service.response.ApproveLoanResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationControllerTest {

    @InjectMocks
    LoanApplicationController loanApplicationController;

    @Mock
    LoanApplicationService loanApplicationService;

    @Mock
    ResponseFactory responseFactory;

    LocalValidatorFactoryBean validatorFactoryBean;

    private ApplyLoanRequest applyLoanRequest;
    private ListLoanRequest listLoanRequest;

    @Before
    public void setup(){
        applyLoanRequest = new ApplyLoanRequest();
        validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.afterPropertiesSet();

        listLoanRequest = new ListLoanRequest();
    }

    @Test
    public void applyLoan_invalidCustomerName_shouldBeFailed(){
        applyLoanRequest.setCustomerName("123");
        Set<ConstraintViolation<ApplyLoanRequest>> result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_name_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setCustomerName("yoedi 123");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_name_invalid",result.iterator().next().getMessage());
    }

    @Test
    public void applyLoan_invalidCustomerAge_shouldBeFailed(){
        applyLoanRequest.setCustomerAge("abc");
        Set<ConstraintViolation<ApplyLoanRequest>> result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_age_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setCustomerAge("");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_age_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setCustomerAge("0");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_age_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setCustomerAge("1234");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_age_invalid",result.iterator().next().getMessage());
    }

    @Test
    public void applyLoan_invalidCustomerAddress_shouldBeFailed(){
        applyLoanRequest.setCustomerAddress("@#(*!");
        Set<ConstraintViolation<ApplyLoanRequest>> result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_address_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setCustomerAddress("Street @!*($");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_address_invalid",result.iterator().next().getMessage());
    }

    @Test
    public void applyLoan_invalidCustomerOccupation_shouldBeFailed(){
        applyLoanRequest.setCustomerOccupation("123");
        Set<ConstraintViolation<ApplyLoanRequest>> result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_occupation_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setCustomerOccupation("Engin33r");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("customer_occupation_invalid",result.iterator().next().getMessage());
    }

    @Test
    public void applyLoan_invalidCitizenID_shouldBeFailed(){
        applyLoanRequest.setCitizenID("123456789");
        Set<ConstraintViolation<ApplyLoanRequest>> result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("citizenid_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setCitizenID("qwerty");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("citizenid_invalid",result.iterator().next().getMessage());
    }

    @Test
    public void applyLoan_invalidApplicationID_shouldBeFailed(){
        applyLoanRequest.setCitizenID("123456789");
        Set<ConstraintViolation<ApplyLoanRequest>> result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("citizenid_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setCitizenID("qwerty");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("citizenid_invalid",result.iterator().next().getMessage());
    }

    @Test
    public void applyLoan_invalidLoanAmount_shouldBeFailed(){
        applyLoanRequest.setLoanAmount("99");
        Set<ConstraintViolation<ApplyLoanRequest>> result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("loan_amount_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setLoanAmount("qwerty");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("loan_amount_invalid",result.iterator().next().getMessage());
    }

    @Test
    public void applyLoan_invalidTerm_shouldBeFailed(){
        applyLoanRequest.setTerm("11");
        Set<ConstraintViolation<ApplyLoanRequest>> result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("term_invalid",result.iterator().next().getMessage());

        applyLoanRequest.setTerm("qwerty");
        result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals("term_invalid",result.iterator().next().getMessage());
    }

    @Test
    public void listLoan_invalidCitizenID_shouldBeFailed(){
        listLoanRequest.setCitizenID("123456789");
        Set<ConstraintViolation<ListLoanRequest>> result = validatorFactoryBean.validate(listLoanRequest);
        assertEquals("citizenid_invalid",result.iterator().next().getMessage());

        listLoanRequest.setCitizenID("qwerty");
        result = validatorFactoryBean.validate(listLoanRequest);
        assertEquals("citizenid_invalid",result.iterator().next().getMessage());
    }

    @Test
    public void applyLoan_validRequest_shouldBeSuccess(){
        applyLoanRequest.setTerm("12");
        applyLoanRequest.setLoanAmount("1000");
        applyLoanRequest.setCitizenID("0123456789");
        applyLoanRequest.setCustomerOccupation("engineer");
        applyLoanRequest.setCustomerAddress("Bangkok, Thailand");
        applyLoanRequest.setCustomerAge("26");
        applyLoanRequest.setCustomerName("Yoedi Hariadi Kurniawan");
        applyLoanRequest.setApplicationID("0987654321");

        Set<ConstraintViolation<ApplyLoanRequest>> result = validatorFactoryBean.validate(applyLoanRequest);
        assertEquals(0,result.size());
    }

    @Test
    public void listLoan_validRequest_shouldBeSuccess(){
        listLoanRequest.setCitizenID("0123456789");

        Set<ConstraintViolation<ListLoanRequest>> result = validatorFactoryBean.validate(listLoanRequest);
        assertEquals(0,result.size());
    }

    @Test
    public void applyLoan_throwException_shouldBeError() throws Exception{
        ApplyLoanRequest request = new ApplyLoanRequest();
        when(loanApplicationService.applyingLoan(any(),any(),any(),any(),any(),any(),any(),any())).thenThrow(Exception.class);
        when(responseFactory.error(any(),any())).thenCallRealMethod();
        ResponseEntity response = loanApplicationController.applyLoan(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void applyLoan_success() throws Exception{
        ApproveLoanResponse response = new ApproveLoanResponse();
        response.setCustomer(new Customer());
        response.setLoan(new Loan());
        when(loanApplicationService.applyingLoan(any(),any(),any(),any(),any(),any(),any(),any())).thenReturn(response);
        when(responseFactory.success(any(),any())).thenCallRealMethod();
        ResponseEntity responseEntity = loanApplicationController.applyLoan(new ApplyLoanRequest());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void listLoan_throwException_shouldBeError() throws Exception{
        ListLoanRequest request = new ListLoanRequest();
        when(loanApplicationService.getLoan(any())).thenThrow(Exception.class);
        when(responseFactory.error(any(),any())).thenCallRealMethod();
        ResponseEntity response = loanApplicationController.listLoan(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void listLoan_success() throws Exception{
        List<Loan> response = new ArrayList<>();
        response.add(new Loan());
        when(loanApplicationService.getLoan(any())).thenReturn(response);
        when(responseFactory.success(any(),any())).thenCallRealMethod();
        ResponseEntity responseEntity = loanApplicationController.listLoan(new ListLoanRequest());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

}
