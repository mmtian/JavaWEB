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

}
