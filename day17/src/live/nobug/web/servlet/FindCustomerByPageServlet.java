package live.nobug.web.servlet;

import live.nobug.domain.Customer;
import live.nobug.domain.PageBean;
import live.nobug.service.CustomerService;
import live.nobug.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findCustomerByPage")
public class FindCustomerByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> condition = request.getParameterMap();

        CustomerService service = new CustomerServiceImpl();
        PageBean<Customer> customerPage = service.findCustomerByPage(condition);

        request.setAttribute("customerPage", customerPage);
        request.setAttribute("condition", condition);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
