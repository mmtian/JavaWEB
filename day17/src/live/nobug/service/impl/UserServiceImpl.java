package live.nobug.service.impl;

import live.nobug.dao.UserDao;
import live.nobug.dao.impl.UserDaoImpl;
import live.nobug.domain.User;
import live.nobug.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
