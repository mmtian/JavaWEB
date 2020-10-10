package live.nobug.dao;

import live.nobug.domain.User;

public interface UserDao {
    User findByUsernameAndPassword(String username, String password);
}
