import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static config.DatabaseConfiguration.*;

@WebServlet("/CheckUserNumbers")
public class CheckUserNumbers extends HttpServlet {

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    private Connection conn;
    private Statement stmt;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> decodedNumbers;
        List<String> adminNumbers = new ArrayList<>();
        String password = (String) req.getSession().getAttribute("password");
        String fileName = TMP_DIR + "\\" + password.substring(0, 20) + ".txt";

        // Request for admin numbers
        try {
            decodedNumbers = GetUserNumbers.getDecryptedNumbers(req);
            // create database connection and statement
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // query database and get results
            ResultSet rs = stmt.executeQuery("SELECT * FROM adminNumbers");
            while (rs.next()) {
                adminNumbers.add(rs.getString("Numbers"));
            }

            // close connection
            conn.close();

            // Checking user numbers with admin numbers
            boolean checkResult = checkNumbers(decodedNumbers, adminNumbers);
            if (checkResult) {
                File myObj = new File(fileName);
                myObj.delete();
            }

            // display output.jsp page with given content above if successful
            RequestDispatcher dispatcher = req.getRequestDispatcher("/account.jsp");
            req.setAttribute("message", checkResult ? "Win" : "Lost");
            dispatcher.forward(req, resp);

        } catch (IOException e) {
            // display error.jsp page with given message if successful
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            req.setAttribute("message", "Read/Write file Error, Please try again");
            dispatcher.forward(req, resp);
        } catch (SQLException sqe) {
            // display error.jsp page with given message if successful
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            req.setAttribute("message", "Database Error, Please try again");
            dispatcher.forward(req, resp);
        } catch (Exception se) {
            se.printStackTrace();
            // display error.jsp page with given message if successful
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            req.setAttribute("message", "Technical error");
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


    private boolean checkNumbers(List<String> decodedNumbers, List<String> adminNumbers) {
        for (String admNumb : adminNumbers) {
            List<String> numbers = Arrays.asList(admNumb.split(","));

            for (String userNum : decodedNumbers) {
                boolean foundWin = false;
                for (String number : userNum.split(",")) {
                    foundWin = numbers.contains(number);
                    if (!foundWin) break;
                }
                if (foundWin) return true;
            }
        }
        return false;
    }


}
