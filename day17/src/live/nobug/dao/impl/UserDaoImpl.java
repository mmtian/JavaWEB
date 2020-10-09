package live.nobug.dao.impl;

import live.nobug.dao.UserDao;
import live.nobug.domain.User;
import live.nobug.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        try{
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
