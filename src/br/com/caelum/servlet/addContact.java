package br.com.caelum.servlet;

import br.com.caelum.bean.Contact;
import br.com.caelum.dao.ContactDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/addContact")
public class addContact extends HttpServlet {
    private int countUsers = 0;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("Start servlet: " + this.getClass());
    }

    @Override
    protected void service(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        countUsers++;
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String dateText = request.getParameter("birthDate");
        Calendar birthDate = null;
        
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateText);
            birthDate = Calendar.getInstance();
            birthDate.setTime(date);
        } catch (ParseException error) {
            out.println("Convertion error: " + error.getMessage());
            return ;
        }
        
        Contact contact = new Contact();
        contact.setName(name);
        contact.setAddress(address);
        contact.setEmail(email);
        contact.setDateText(dateText);
        
        ContactDAO contactDAO = new ContactDAO();
        contactDAO.saveContact(contact);
        
        out.println("<html>");
        out.println("<body>");
        out.println("<p>User: " + countUsers + "</p>");
        out.println("<p>Contact saved</p>");
        out.println("<p>Name: " + contact.getName() + "</p>");
        out.println("<p>Address: " + contact.getAddress()+ "</p>");
        out.println("<p>Email: " + contact.getEmail()+ "</p>");
        out.println("<p>Birth date: " + contact.getBirthDate()+ "</p>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void destroy() {
        super.destroy();
        log("Closing servlet: " + this.getClass());
    }
}
