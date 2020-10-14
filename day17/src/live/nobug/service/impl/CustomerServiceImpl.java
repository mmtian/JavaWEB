package live.nobug.service.impl;

import live.nobug.dao.CustomerDao;
import live.nobug.dao.impl.CustomerDaoImpl;
import live.nobug.domain.Customer;
import live.nobug.domain.PageBean;
import live.nobug.service.CustomerService;

import java.util.List;
import java.util.Map;

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
    public PageBean<Customer> findCustomerByPage(Map<String, String[]> condition) {
        int currentPage = 1;
        int rowsPerPage = 5;
        String name = "";
        String address = "";
        String email = "";

        if (condition.get("currentPage") != null && condition.get("currentPage").length > 0) {
            int temp = Integer.parseInt(condition.get("currentPage")[0]);
            currentPage = temp > 0 ? temp : 1;
        }
        if (condition.get("rowsPerPage") != null && condition.get("rowsPerPage").length > 0) {
            rowsPerPage = Integer.parseInt(condition.get("rowsPerPage")[0]);
        }
        if (condition.get("name") != null && condition.get("name").length > 0) {
            name = condition.get("name")[0];
        }
        if (condition.get("address") != null && condition.get("address").length > 0) {
            address = condition.get("address")[0];
        }
        if (condition.get("email") != null && condition.get("email").length > 0) {
            email = condition.get("email")[0];
        }

        PageBean<Customer> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRowsPerPage(rowsPerPage);

        int totalCount = dao.totalCount(name, address, email);
        pb.setTotalCount(totalCount);

        int totalPage = totalCount % rowsPerPage == 0 ? totalCount / rowsPerPage : totalCount / rowsPerPage + 1;
        pb.setTotalPage(totalPage);

        int startIndex = (currentPage - 1) * rowsPerPage;
        List<Customer> customers = dao.findByCondition(startIndex, rowsPerPage, name, address, email);
        pb.setList(customers);

        return pb;
    }
}
