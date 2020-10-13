package live.nobug.test;

import live.nobug.domain.Customer;
import live.nobug.domain.PageBean;
import live.nobug.domain.User;
import live.nobug.service.CustomerService;
import live.nobug.service.UserService;
import live.nobug.service.impl.CustomerServiceImpl;
import live.nobug.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserTest {
    @Test
    public void test1(){
        UserService service = new UserServiceImpl();
        User user = new User();
        user.setUsername("tmm");
        user.setPassword("123");

        User login = service.login(user);
        System.out.println(login);

    }

    @Test
    public void test2(){
        CustomerService service = new CustomerServiceImpl();
        PageBean<Customer> pageBean = service.findCustomerByPage("1", "10");
        System.out.println(pageBean);
    }
}
