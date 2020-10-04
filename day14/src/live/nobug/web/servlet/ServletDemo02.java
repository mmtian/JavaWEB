package live.nobug.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/demo02")
public class ServletDemo02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost...");

        BufferedReader reader = request.getReader();

        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet...");
        BufferedReader reader = request.getReader();

        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
