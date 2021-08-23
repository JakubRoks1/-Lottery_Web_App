import utils.EncryptionUtils;
import utils.model.AESencryption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/AddUserNumbers")
public class AddUserNumbers extends HttpServlet {

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String combinedNumbers = AddAdminNumbers.getCombinedNumbers(req);
        HttpSession httpSession = req.getSession();
        String password = (String) httpSession.getAttribute("password");
        String encryptedNumbers = "";
        AESencryption aeSencryption = getAndSetAeSencryption(httpSession);

        try {
            byte[] bytes = EncryptionUtils.do_AESEncryption(combinedNumbers, aeSencryption.getSymmetricKey(), aeSencryption.getInitializationVector());
            encryptedNumbers = Base64.getEncoder().encodeToString(bytes);

            String fileName = TMP_DIR + "\\" + password.substring(0, 20) + ".txt";
            this.appendToFile(fileName, encryptedNumbers);
        }
        catch (IOException e) {
            // display error.jsp page with given message if successful
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            req.setAttribute("message", "Read/Write file Error, Please try again");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            // display error.jsp page with given message if successful
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            req.setAttribute("message", "Technical error");
            dispatcher.forward(req, resp);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/account.jsp");
        req.setAttribute("message", "You have successfully added your numbers");
        dispatcher.forward(req, resp);
    }

    private AESencryption getAndSetAeSencryption(HttpSession httpSession) {
        AESencryption aeSencryption;
        if (httpSession.getAttribute("keyPair") == null) {
            aeSencryption = new AESencryption();
            aeSencryption.setInitializationVector(EncryptionUtils.createInitializationVector());
            try {
                aeSencryption.setSymmetricKey(EncryptionUtils.createAESKey());
            } catch (Exception e) {

            }
            httpSession.setAttribute("keyPair", aeSencryption);
        } else {
            aeSencryption = (AESencryption) httpSession.getAttribute("keyPair");
        }
        return aeSencryption;
    }

    private void appendToFile(String fileName, String content) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.newLine();
        bw.close();
    }

}


