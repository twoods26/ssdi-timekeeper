package edu.ssdi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.ssdi.model.EmpTimekeeper;
import edu.ssdi.model.LoginBean;
import edu.ssdi.util.Database;



public class LoginDao {
	
	private Connection connection;
	  
    public LoginDao() {
        connection = Database.getConnection();
}

    public int validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;
        int empId =0;

        Class.forName("com.mysql.jdbc.Driver");

        //connecting to mysql database
        try (Connection connection = DriverManager
            .getConnection ("jdbc:mysql://localhost:3306/timekeepersystem", "root","Welcome123"); 
        		

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection
            .prepareStatement("select empId from useraccount where username = ? and password = ? ")) {
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            if ((rs.next()))
            {
            
              empId = rs.getInt("empId");
              System.out.println(empId);
            }
            
            else 
            {
            	System.out.println("login fail");
            }
        }
        
            catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return empId;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}