package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name="helloworld",
    urlPatterns={"/helloworld", "/hello"},
    initParams={
        @WebInitParam(name="parameter01", value="xelo"),
        @WebInitParam(name="parameter02", value="ximira")
    }
)
public class HelloWorld extends HttpServlet {
    private String parameter01;
    private String parameter02;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.parameter01 = config.getInitParameter("parameter01");
        this.parameter02 = config.getInitParameter("parameter02");
    }

    @Override
    protected void service(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<body>");
        out.println("<p>Hello World</p>");
        out.println("<p>Parameter 01: " + this.parameter01 + "</p>");
        out.println("<p>Parameter 02: " + this.parameter02 + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
