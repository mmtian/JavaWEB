package live.nobug.service.impl;

import live.nobug.dao.CustomerDao;
import live.nobug.dao.impl.CustomerDaoImpl;
import live.nobug.domain.Customer;
import live.nobug.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao dao = new CustomerDaoImpl();
    @Override
    public List<Customer> findAll() {
        return dao.findAll();
    }

    @Override
    public void add(Customer customer) {
        dao.add(customer);
    }

    @Override
    public void deleteCustomerById(String id) {
        dao.deleteById(Integer.parseInt(id));
    }

    @Override
    public Customer findCustomerById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateCustomer(Customer customer) {
        dao.update(customer);
    }
}
