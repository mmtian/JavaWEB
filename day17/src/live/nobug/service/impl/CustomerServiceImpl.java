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
}
