package live.nobug.download;

import live.nobug.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String filename = request.getParameter("filename");
        // 获取ServletContext对象
        ServletContext context = this.getServletContext();
        // 获取文件真实路径
        String path = context.getRealPath("/WEB-INF/files/" + filename);

        // 设置MIME类型
        response.setContentType(context.getMimeType(filename));

        // 解决中文文件名乱码
        String agent = request.getHeader("user-agent");
        System.out.println(agent);
        filename = DownLoadUtils.getFileName(agent, filename);
        System.out.println(filename);

        // 设置响应头
        response.setHeader("content-disposition", "attachment;filename=" + filename);

        // 文件对拷
        FileInputStream fis = new FileInputStream(path);
        ServletOutputStream sos = response.getOutputStream();

        byte[] bytes = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            sos.write(bytes, 0, len);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
