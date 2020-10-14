package live.nobug.service;

import live.nobug.domain.Customer;
import live.nobug.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<Customer> findAll();

    void add(Customer customer);

    void deleteCustomerById(String id);

    Customer findCustomerById(String id);

    void updateCustomer(Customer customer);

    void delSelected(String[] ids);

    PageBean<Customer> findCustomerByPage(Map<String, String[]> condition);
}
