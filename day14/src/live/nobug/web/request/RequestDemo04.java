package live.nobug.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * 演示request对象获取请求参数
 * GET和POST通用
 *
 */
@WebServlet("/requestDemo04")
public class RequestDemo04 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取单个值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        /*System.out.println(username);
        System.out.println(password);*/

        // 获取多个值
        String[] hobbies = request.getParameterValues("hobby");
        /*for (String hobby : hobbies) {
            System.out.println(hobby);
        }*/

        // 获取所有参数名
        Enumeration<String> names = request.getParameterNames();
        /*while (names.hasMoreElements()){
            String name = names.nextElement();
            System.out.println(name);
        }*/

        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for (String key : keySet) {
            System.out.println(key);
            String[] values = parameterMap.get(key);
            for (String value : values) {
                System.out.println(value);
            }
            System.out.println("============");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //直接调用post
        doPost(request, response);
    }
}
