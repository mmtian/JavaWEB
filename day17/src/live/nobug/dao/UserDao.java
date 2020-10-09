package live.nobug.dao;

import live.nobug.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findByUsernameAndPassword(String username, String password);
}
