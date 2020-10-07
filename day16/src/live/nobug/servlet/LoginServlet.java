package live.nobug.servlet;

import live.nobug.dao.UserDao;
import live.nobug.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");

        String checkCode = request.getParameter("checkCode");
        String checkCodeSession = (String) request.getSession().getAttribute("check_code_session");
        request.getSession().removeAttribute("check_code_session");

        if (checkCodeSession != null && checkCodeSession.equalsIgnoreCase(checkCode)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User loginUser = new User();
            loginUser.setUsername(username);
            loginUser.setPassword(password);

            User user = new UserDao().login(loginUser);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/success.jsp");
            } else {
                request.setAttribute("login_error", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("cc_error", "验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
