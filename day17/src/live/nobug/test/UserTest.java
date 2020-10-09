package live.nobug.test;

import live.nobug.domain.User;
import live.nobug.service.UserService;
import live.nobug.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class UserTest {
    @Test
    public void test1(){
        UserService service = new UserServiceImpl();
        User user = new User();
        user.setUsername("tmm");
        user.setPassword("123");

        User login = service.login(user);
        System.out.println(login);
        System.out.println("==============");
        List<User> users = service.findAll();
        System.out.println(users);
    }
}
