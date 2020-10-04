package live.nobug.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 演示request获取请求头
 */
@WebServlet("/requestDemo02")
public class RequestDemo02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String agent = request.getHeader("user-agent");
        if(agent.contains("Chrome")){
            System.out.println("谷歌浏览器...");
        }else if(agent.contains("Firefox")){
            System.out.println("火狐浏览器...");
        }

        System.out.println("==========================");
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
    }
}
