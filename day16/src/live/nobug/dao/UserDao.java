package live.nobug.dao;

import live.nobug.domain.User;
import live.nobug.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User loginUSer){
        try{
            String sql = "select * from user where username = ? and password = ?";

            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    loginUSer.getUsername(),
                    loginUSer.getPassword());
            return user;
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
