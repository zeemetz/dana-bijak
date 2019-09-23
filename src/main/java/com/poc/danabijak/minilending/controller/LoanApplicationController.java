package com.poc.danabijak.minilending.controller;

import com.poc.danabijak.minilending.constant.ResponseConstant;
import com.poc.danabijak.minilending.controller.request.ApplyLoanRequest;
import com.poc.danabijak.minilending.controller.request.ListLoanRequest;
import com.poc.danabijak.minilending.controller.response.ApplyLoanResponse;
import com.poc.danabijak.minilending.controller.response.ListLoanResponse;
import com.poc.danabijak.minilending.factory.ResponseFactory;
import com.poc.danabijak.minilending.repository.entity.Customer;
import com.poc.danabijak.minilending.repository.entity.Loan;
import com.poc.danabijak.minilending.service.LoanApplicationService;
import com.poc.danabijak.minilending.service.response.ApproveLoanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/v1")
@RestController
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Autowired
    private ResponseFactory responseFactory;

    @PostMapping(value = "/loan/apply")
    @ResponseBody
    public ResponseEntity applyLoan (@RequestBody ApplyLoanRequest request){
        ApplyLoanResponse response = new ApplyLoanResponse();
        try{
            ApproveLoanResponse approveLoanResponse = loanApplicationService.applyingLoan(
                    request.getCustomerName(),
                    request.getCustomerAge(),
                    request.getCustomerAddress(),
                    request.getCustomerOccupation(),
                    request.getApplicationID(),
                    request.getCitizenID(),
                    request.getLoanAmount(),
                    request.getTerm());

            response.setCitizenID(approveLoanResponse.getCustomer().getCitizenID());
            response.setApplicationID(approveLoanResponse.getLoan().getApplicationID());
            response.setCustomerID(approveLoanResponse.getCustomer().getCustomerID());
            response.setLoanID(approveLoanResponse.getLoan().getLoanID());
        } catch (Exception e){
            e.printStackTrace();
            return responseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, ResponseConstant.INTERNAL_SERVER_ERROR);
        }
        return responseFactory.success(response, ApplyLoanResponse.class);
    }

    @GetMapping(value = "/loan/list")
    @ResponseBody
    public ResponseEntity listLoan (@RequestBody ListLoanRequest request) {
        ListLoanResponse response = new ListLoanResponse();
        try{
            List<Loan> loanResult = loanApplicationService.getLoan(request.getCitizenID());
            response.setLoans(loanResult);
        }catch (Exception e){
            e.printStackTrace();
            return responseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, ResponseConstant.INTERNAL_SERVER_ERROR);
        }
        return responseFactory.success(response,ListLoanResponse.class);
    }
}
