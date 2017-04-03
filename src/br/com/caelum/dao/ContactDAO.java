package br.com.caelum.dao;

import br.com.caelum.bean.Contact;
import br.com.caelum.util.DateUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

public class ContactDAO {
    private Connection connection;
    private PreparedStatement statement;

    public ContactDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveContact(Contact contact) throws ServletException {
        String sql = "INSERT INTO contact " +
            "(name, address, email, birthDate) VALUES (?, ?, ?, ?)";
        
        try {
            prepareStatement(sql);
            populateInsertStatement(contact);
            statement.execute();
            statement.close();
        } catch (SQLException error) {
            throw new ServletException(error);
        }
    }
    
    private void populateInsertStatement(Contact contact) throws SQLException {
        statement.setString(1, contact.getName());
        statement.setString(2, contact.getAddress());
        statement.setString(3, contact.getEmail());
        statement.setDate(4, new Date(contact.getBirthDate().getTimeInMillis()));
    }
    
    private void prepareStatement(String sql) throws SQLException {
        statement = connection.prepareStatement(sql);
    }
}
