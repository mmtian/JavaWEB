package live.nobug.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

//@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    List<String> list = new ArrayList<>();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("getParameter")){
                    String value = (String) method.invoke(req, args);
                    if(value != null && !value.equals("")){
                        for (String s : list) {
                            if(value.contains(s)){
                                value = value.replaceAll(s, "***");
                            }
                        }
                    }
                    return value;
                }
                String value = (String) method.invoke(req, args);
                return value;
            }
        });

        chain.doFilter(proxy_req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        String realPath = config.getServletContext().getRealPath("/WEB-INF/classes/sensitive_words.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(realPath));
            String line;
            while ((line = reader.readLine())!=null){
                list.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
