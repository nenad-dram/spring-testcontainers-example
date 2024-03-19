package com.endyary.springtestcontainers;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@ExtendWith(PostgreSQLExtension.class)
@DirtiesContext
class CustomerRepositoryTest {

  @Autowired
  CustomerRepository customerRepository;

  @Test
  void saveCustomer() {
    Customer customer = new Customer("John", "john@mail.com");
    Customer savedCustomer = customerRepository.save(customer);

    Optional<Customer> dbCustomer = customerRepository.findById(savedCustomer.getId());

    Assertions.assertTrue(dbCustomer.isPresent());
    Assertions.assertEquals("John", dbCustomer.get().getName());
  }

  @Test
  void deleteCustomer() {
    Customer customer = new Customer("Dennis", "dennis@mail.com");
    Customer savedCustomer = customerRepository.save(customer);

    customerRepository.deleteById(savedCustomer.getId());
    Optional<Customer> dbCustomer = customerRepository.findById(savedCustomer.getId());

    Assertions.assertTrue(dbCustomer.isEmpty());
  }

}
