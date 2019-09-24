package com.poc.danabijak.minilending.repository;

import com.poc.danabijak.minilending.repository.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class CustomerRepositoryTest {

    private String citizenID = "01234567890";

    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setup(){
        Customer customer = new Customer();
        customer.setName("yoedi hariadi kurniawan");
        customer.setCitizenID(citizenID);
        customer.setAge(26);
        customer.setAddress("Bangkok, Thailand");
        customer.setOccupation("engineer");

        customerRepository.save(customer);
    }

    @Test
    public void findByCitizenID_found(){
        Customer customer = customerRepository.findByCitizenID(citizenID);
        Assert.assertNotNull(customer);
        Assert.assertNotNull("",customer.getCustomerID());
    }

    @Test
    public void findByCitizenID_notFound(){
        Customer customer = customerRepository.findByCitizenID("none");
        Assert.assertNull(customer);
    }

}
