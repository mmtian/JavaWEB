package live.nobug.service.impl;

import live.nobug.dao.UserDao;
import live.nobug.dao.impl.UserDaoImpl;
import live.nobug.domain.User;
import live.nobug.service.UserService;


public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
