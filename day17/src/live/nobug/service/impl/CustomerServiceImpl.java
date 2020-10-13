package live.nobug.service.impl;

import live.nobug.dao.CustomerDao;
import live.nobug.dao.impl.CustomerDaoImpl;
import live.nobug.domain.Customer;
import live.nobug.domain.PageBean;
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

    @Override
    public void delSelected(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                dao.deleteById(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<Customer> findCustomerByPage(String currentPage_str, String rowsPerPage_str) {
        int currentPage = Integer.parseInt(currentPage_str);
        int rowsPerPage = Integer.parseInt(rowsPerPage_str);

        PageBean<Customer> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRowsPerPage(rowsPerPage);

        int totalCount = dao.totalCount();
        pb.setTotalCount(totalCount);

        int totalPage = totalCount % rowsPerPage == 0 ? totalCount / rowsPerPage : totalCount / rowsPerPage + 1;
        pb.setTotalPage(totalPage);

        int startIndex = (currentPage - 1) * rowsPerPage;
        List<Customer> customers = dao.findByPage(startIndex, rowsPerPage);
        pb.setList(customers);

        return pb;
    }
}
