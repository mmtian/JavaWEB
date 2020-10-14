package live.nobug.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 强转
        HttpServletRequest request = (HttpServletRequest) req;

        String uri = request.getRequestURI();
        if(uri.contains("/login.jsp") || uri.contains("/checkCode") || uri.contains("loginServlet") || uri.contains("/css/")|| uri.contains("/js/")){
            chain.doFilter(req, resp);
        }else {
            Object user = request.getSession().getAttribute("user");
            if(user != null){
                chain.doFilter(req, resp);
            }else {
                request.setAttribute("login_msg", "您尚未登录，请登录！");
                request.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
