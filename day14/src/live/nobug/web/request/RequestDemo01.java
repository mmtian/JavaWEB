package live.nobug.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示request对象获取请求行
 */
@WebServlet("/requestDemo01")
public class RequestDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //请求方法
        String method = request.getMethod();
        System.out.println(method);

        //虚拟目录
        String contextPath = request.getContextPath();
        System.out.println(contextPath);

        //servlet路径
        String servletPath = request.getServletPath();
        System.out.println(servletPath);

        //查询字符串
        String queryString = request.getQueryString();
        System.out.println(queryString);

        //URI
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //URL
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);

        //协议
        String protocol = request.getProtocol();
        System.out.println(protocol);

        //客户端IP
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
    }
}
