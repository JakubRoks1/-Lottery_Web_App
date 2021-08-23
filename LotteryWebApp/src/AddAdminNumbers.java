import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static config.DatabaseConfiguration.*;

@WebServlet("/AddAdminNumbers")
public class AddAdminNumbers extends HttpServlet {

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    private Connection conn;
    private PreparedStatement stmt;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String combinedNumbers = getCombinedNumbers(req);

        try {

            // create database connection and statement
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create sql query
            String query = "INSERT INTO adminNumbers (Numbers)"
                    + " VALUES (?)";


            // set values into SQL query statement
            stmt = conn.prepareStatement(query);
            stmt.setString(1, combinedNumbers);

            // execute query and close connection
            stmt.execute();
            conn.close();

            // display account.jsp page with given message if successful
            resp.sendRedirect("AdminHome");

        } catch (Exception se) {
            se.printStackTrace();
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            req.setAttribute("message", se.getMessage());
            dispatcher.forward(req, resp);
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

    static String getCombinedNumbers(HttpServletRequest req) {
        String number1 = req.getParameter("number1");
        String number2 = req.getParameter("number2");
        String number3 = req.getParameter("number3");
        String number4 = req.getParameter("number4");
        String number5 = req.getParameter("number5");
        String number6 = req.getParameter("number6");

        return number1 + "," + number2 + "," + number3 + "," + number4 + "," + number5 + "," + number6;
    }
}
