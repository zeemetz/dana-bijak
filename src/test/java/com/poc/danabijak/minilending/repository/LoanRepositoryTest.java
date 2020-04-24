package com.poc.minilending.repository;

import com.poc.minilending.repository.entity.Loan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class LoanRepositoryTest {
    @Autowired
    LoanRepository loanRepository;

    private long customerID = Long.valueOf("-8551933125343514977");

    @Before
    public void setup(){
        Loan loan = new Loan();
        loan.setCustomerID(customerID);
        loanRepository.save(loan);
    }

    @Test
    public void findAllByCustomerID_found(){
        List<Loan> loans = loanRepository.findAllByCustomerID(customerID);
        Assert.assertNotEquals(0,loans.size());
        Assert.assertNotNull(loans.get(0).getLoanID());
    }

    @Test
    public void findAllByCustomerID_noFound(){
        List<Loan> loans = loanRepository.findAllByCustomerID(Long.valueOf(0));
        Assert.assertEquals(0,loans.size());
    }
}
