package live.nobug.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    List<String> list = new ArrayList<>();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 增强getParameter 方法
                if(method.getName().equals("getParameter")){
                    String value = (String) method.invoke(req, args);
                    return replace(value);
                }

                // 增强getParameter 方法
                if(method.getName().equals("getParameters")){
                    String[] values = (String[]) method.invoke(req, args);
                    if(values != null){
                        for (int i = 0; i < values.length; i++) {
                            values[i] = replace(values[i]);
                        }
                    }
                    return values;
                }

                // 增强getParameterMap方法
                if(method.getName().equals("getParameterMap")){
                    Map<String, String[]> map = (Map<String, String[]>) method.invoke(req, args);
                    Set<String> keySet = map.keySet();
                    for (String key : keySet) {
                        String[] values = map.get(key);
                        if(values != null){
                            for (int i = 0; i < values.length; i++) {
                                values[i] = replace(values[i]);
                            }
                        }
                    }
                    return map;
                }

                return method.invoke(req, args);
            }
        });

        chain.doFilter(proxy_req, resp);
    }

    private String replace(String value){
        if(value != null && !value.equals("")){
            for (String s : list) {
                if(value.contains(s)){
                    value = value.replaceAll(s, "***");
                }
            }
        }
        return value;
    }

    public void init(FilterConfig config) throws ServletException {
        String realPath = config.getServletContext().getRealPath("/WEB-INF/classes/sensitive_words.txt");
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(realPath),"utf-8");
            BufferedReader reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine())!=null){
                list.add(line);
            }
            System.out.println(list);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
