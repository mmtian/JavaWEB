package live.nobug.service;

import live.nobug.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    void add(Customer customer);
}
