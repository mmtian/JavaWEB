package live.nobug.dao;

import live.nobug.domain.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> findAll();

    void add(Customer customer);

    void deleteById(int id);

    Customer findById(int id);

    void update(Customer customer);
}
