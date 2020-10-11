package live.nobug.dao.impl;

import live.nobug.dao.CustomerDao;
import live.nobug.domain.Customer;
import live.nobug.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Customer> findAll() {
        String sql = "select * from customer";
        List<Customer> customers = template.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        return customers;
    }

    @Override
    public void add(Customer customer) {
        String sql = "insert into customer values(null, ?, ?, ?, ?, ?, ?)";
        template.update(sql, customer.getName(), customer.getGender(), customer.getAge(), customer.getAddress(), customer.getQq(), customer.getEmail());
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from customer where id = ?";
        template.update(sql, id);
    }

    @Override
    public Customer findById(int id) {
        String sql = "select * from customer where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), id);
    }

    @Override
    public void update(Customer customer) {
        String sql = "update customer set name = ?, gender = ?, age = ?, address = ?, qq = ?, email = ? where id = ?";
        template.update(sql, customer.getName(), customer.getGender(), customer.getAge(), customer.getAddress(), customer.getQq(), customer.getEmail(), customer.getId());
    }

}
