package live.nobug.service;

import live.nobug.domain.Customer;
import live.nobug.domain.PageBean;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    void add(Customer customer);

    void deleteCustomerById(String id);

    Customer findCustomerById(String id);

    void updateCustomer(Customer customer);

    void delSelected(String[] ids);

    PageBean<Customer> findCustomerByPage(String currentPage, String rowsPerPage);
}
