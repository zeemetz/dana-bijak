# Mini-Lending Apps

Given Cases:
Please prepare lending application mini version. Create REST endpoints for applying for loan (enter amount and term), listing all active loans (mandatory fields issue date, due date, term, interest). All inputs should be validated. You can store loan object in database after (no risk assessment logic). Please provide tests.
Technology requirements:
* Java 8
* Spring
* Hibernate
* Any embedded application container Any in memory SQL relational database
* Project should be launched of the box (no database setup, no editing properties files). Please also prepare build and launch instructions.


## Getting Started

The project are fully containerized and need to run under docker.
To build, need maven ready

### Prerequisites

install maven in order to build the application

```
https://maven.apache.org/install.html
```

### Installing

How to build

```
mvn clean install
```

How to build docker image

```
docker-compose build
```

How to run

```
docker-compose up -d
```

Test the api using postman bellow:
https://www.getpostman.com/collections/ca340c48afab32a132ee

## Running the tests

### Mockito Test

unit test done in mock, there are 2 integration test to check repository to work (ignored when build) with style as follow:

```java
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
```

### Coding style tests

Unit test are created as to check its sole function to keep the loose coupling

```java
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
```

## Code management

git flow are used in order to keep clean branches
explanation on git flow
https://danielkummer.github.io/git-flow-cheatsheet/

## Built With

* Lombok
* Spring boot framework
* Redis Alpine
* Java openJDK
* Lettuce redis client
* Mockito

## Versioning

initial master version 0.1.0

## Authors

* **Yoedi Hariadi Kurniawan**

See also:
* [github](http://github.com/zeemetz)
* [bitbucket](http://bitbucket.org/yeeha)
* [medium](http://medium.com/@yodyhariadi_93)

