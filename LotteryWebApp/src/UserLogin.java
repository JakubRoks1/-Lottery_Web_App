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
import java.util.Enumeration;

import static config.DatabaseConfiguration.*;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {

    private Connection conn;
    private Statement stmt;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Integer failedLoginCounter;
        if (request.getParameter("failedLoginCounter") == null) {
            failedLoginCounter = 0;
        }
        else {
            failedLoginCounter = Integer.parseInt(request.getParameter("failedLoginCounter")) ;
        }

        //httpSession.invalidate();
        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        //attributeNames.
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            if (!"keyPair".equals(attributeName)) {
                httpSession.removeAttribute(attributeName);
            }

        }

        // get parameter data that was submitted in HTML form (use form attributes 'name')
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            // create database connection and statement
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // query database and get results
            ResultSet rs = stmt.executeQuery("SELECT * FROM userAccounts WHERE Username='" + username + "' AND Pwd='" + EncryptionUtils.hashMD5(password) + "'");
            String content = "<table border='1' cellspacing='2' cellpadding='2' width='100%' align='left'>" +
                    "<tr><th>First name</th><th>Last name</th><th>Email</th><th>Phone number</th><th>Username</th><th>Password</th></tr>";

            // checking if ResultSet is empty
            if (rs.next() == false) {
                throw new Exception();
            } else {
                do {
                    httpSession.setAttribute("firstname",rs.getString("Firstname"));
                    httpSession.setAttribute("lastname",rs.getString("Lastname"));
                    httpSession.setAttribute("email",rs.getString("Email"));
                    httpSession.setAttribute("username",rs.getString("Username"));
                    httpSession.setAttribute("password", rs.getString("Pwd"));
                    httpSession.setAttribute("user_role",rs.getString("Role"));

                    content += "<tr><td>" + rs.getString("Firstname") + "</td>" +
                            "<td>" + rs.getString("Lastname") + "</td>" +
                            "<td>" + rs.getString("Email") + "</td>" +
                            "<td>" + rs.getString("Phone") + "</td>" +
                            "<td>" + rs.getString("Username") + "</td>" +
                            "<td>" + rs.getString("Pwd") + "</td></tr>";
                } while (rs.next());
            }

            // finish HTML table text
            content += "</table>";

            // close connection
            conn.close();

            // display output.jsp page with given content above if successful
            if ("ADMIN".equals(httpSession.getAttribute("user_role"))) {
                response.sendRedirect("AdminHome");
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/account.jsp");
                request.setAttribute("data", content);
                request.setAttribute("message", "login success");
                dispatcher.forward(request, response);
            }

        } catch (Exception se) {
            se.printStackTrace();
            // display error.jsp page with given message if successful
            failedLoginCounter++;
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            request.setAttribute("message", "login unsuccesful " + failedLoginCounter);
            request.setAttribute("failedLoginCounter",failedLoginCounter);
            dispatcher.forward(request, response);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
