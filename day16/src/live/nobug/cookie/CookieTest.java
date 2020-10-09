package live.nobug.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 获取Cookie
        Cookie[] cookies = request.getCookies();

        // 当前时间字符串
        String str_date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
        // URL编码
        str_date = URLEncoder.encode(str_date, StandardCharsets.UTF_8);

        // 获取lastTime这个cookie
        Cookie lastTime = getCookie(cookies, "lastTime");
        if (lastTime != null) {
            // 获取cookie值并解码
            String value = URLDecoder.decode(lastTime.getValue(), StandardCharsets.UTF_8);
            // 更新cookie
            lastTime.setValue(str_date);
            lastTime.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(lastTime);
            response.getWriter().write("欢迎回来，您上次的访问时间：" + value);
        }else {
            lastTime = new Cookie("lastTime",str_date);
            lastTime.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(lastTime);
            response.getWriter().write("欢迎您，首次访问" );
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name))
                    return cookie;
            }
        }
        return null;
    }
}
