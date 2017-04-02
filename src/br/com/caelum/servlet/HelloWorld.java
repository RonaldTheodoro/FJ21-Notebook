package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="helloworld", urlPatterns={"/helloworld", "/hello"})
public class HelloWorld extends HttpServlet {

    @Override
    protected void service(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("<p>Hello World</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
