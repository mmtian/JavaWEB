package live.nobug.service;

import live.nobug.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User login(User user);
}
