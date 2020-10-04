# Request对象学习笔记
## 1. Request和Response原理
    1. request和response对象是由服务器创建的，我们来使用它们
    2. request对象是来获取请求消息，response对象是来设置响应消息
    3. 请求与响应过程
        (1) 浏览器向服务器发出请求
        (2) Tomcat服务器根据请求URL中的资源路径创建对应的Servlet对象
        (3) Tomcat服务器创建request和response对象，并将请求消息数据封装到request对象中
        (4) Tomcat服务器将request对象和response对象传递给service方法，并调用service方法
        (5) 程序员在service方法中，根据request对象获取请求消息，并将响应消息封装到response对象中
        (6) Tomcat服务器根据response对象构建响应消息，返回给浏览器
## 2. Request继承关系
    ServletRequest		--  接口
    		|继承
    HttpServletRequest	--  接口
    		|实现
    org.apache.catalina.connector.RequestFacade -- 类(tomcat)
## 3. Request功能
### 3.1 获取请求消息数据
#### 3.1.1 获取请求行
    * GET /day14/requestDemo01?name=zhangsan HTTP/1.1
    * 方法：
        1. 获取请求方式 ：GET
            * String getMethod()  
        2. (*)获取虚拟目录：/day14
            * String getContextPath()
        3. 获取Servlet路径: /requestDemo01
            * String getServletPath()
        4. 获取get方式请求参数：name=zhangsan
            * String getQueryString()
        5. (*)获取请求URI：/day14/requestDemo01
            * String getRequestURI():		/day14/requestDemo01
            * StringBuffer getRequestURL(): http://localhost/day14/requestDemo01

            * URL:统一资源定位符
            * URI：统一资源标识符
        
        6. 获取协议及版本：HTTP/1.1
            * String getProtocol()

        7. 获取客户机的IP地址：
            * String getRemoteAddr()
#### 3.1.2 获取请求头
    1. 获取单个请求头
        String agent = request.getHeader("user-agent");
    3. 获取所有请求头名字
        Enumeration<String> names = request.getHeaderNames();
#### 3.1.3 获取请求体(只适用于post请求)
    BufferedReader reader = request.getReader();
    
    String line = null;
    while (null != (line = reader.readLine())){
        System.out.println(line);
    }
### 3.2其他功能
    1. 获取请求参数(同时适用get和post)
        1. String getParameter(String name):根据参数名称获取参数值    username=zs&password=123
        2. String[] getParameterValues(String name):根据参数名称获取参数值的数组  hobby=xx&hobby=game
        3. Enumeration<String> getParameterNames():获取所有请求的参数名称
        4. Map<String,String[]> getParameterMap():获取所有参数的map集合

        * 中文乱码问题：
            * get方式：tomcat 8 已经将get方式乱码问题解决了
            * post方式：会乱码
                * 解决：在获取参数前，设置request的编码request.setCharacterEncoding("utf-8");
    
    2. 请求转发
        
