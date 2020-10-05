package live.nobug.servlet;

import live.nobug.dao.UserDao;
import live.nobug.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        User loginUser = new User();

        /*String username = request.getParameter("username");
        String password = request.getParameter("password");

        loginUser.setUsername(username);
        loginUser.setPassword(password);*/

        try {
            BeanUtils.populate(loginUser, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        if (user == null) {
            System.out.println("登录失败...");
            request.getRequestDispatcher("/fail.html").forward(request, response);
        } else {
            System.out.println("登陆成功...");
            request.setAttribute("user", user);
            request.getRequestDispatcher("/success").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
