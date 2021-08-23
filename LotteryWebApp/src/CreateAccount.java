import utils.EncryptionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

import static config.DatabaseConfiguration.*;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {

    private Connection conn;
    private PreparedStatement stmt;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get parameter data that was submitted in HTML form (use form attributes 'name')
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try{
            if (isInputFieldInvalid(firstname)
                    || isInputFieldInvalid(lastname)
                    || isInputFieldInvalid(email)
                    || isInputFieldInvalid(phone)
                    || isInputFieldInvalid(username)
                    || isInputFieldInvalid(password)
            ) {
                throw new Exception("message : Registration form invalid");
            }
            // create database connection and statement
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // Create sql query
            String query = "INSERT INTO userAccounts (Firstname, Lastname, Email, Phone, Username, Pwd)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";


            // set values into SQL query statement
            stmt = conn.prepareStatement(query);
            stmt.setString(1,firstname);
            stmt.setString(2,lastname);
            stmt.setString(3,email);
            stmt.setString(4,phone);
            stmt.setString(5,username);
            stmt.setString(6, EncryptionUtils.hashMD5(password));

            // execute query and close connection
            stmt.execute();
            conn.close();

            // Add session application
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("firstname",firstname);
            httpSession.setAttribute("lastname",lastname);
            httpSession.setAttribute("email",email);
            httpSession.setAttribute("username",username);
            httpSession.setAttribute("password", EncryptionUtils.hashMD5(password));
            httpSession.setAttribute("user_role", "PUBLIC");

            // display account.jsp page with given message if successful
            RequestDispatcher dispatcher = request.getRequestDispatcher("/account.jsp");
            request.setAttribute("message", firstname+", you have successfully created an account");
            dispatcher.forward(request, response);

        } catch(Exception se){
            se.printStackTrace();
            // display error.jsp page with given message if unsuccessful
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("message", se.getMessage());
            dispatcher.forward(request, response);
        }
        finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){}
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }


    }

    private boolean isInputFieldInvalid(String input) {
        if (input.contains("<")
                || input.contains(">")
                || input.contains("!")
                || input.contains("{")
                || input.contains("}")
                || input.contains("insert")
                || input.contains("into")
                || input.contains("where")
                || input.contains("script")
                || input.contains("delete")
                || input.contains("input")
        ) {
            return true;
        }
        return false;
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
