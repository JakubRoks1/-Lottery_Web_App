import utils.EncryptionUtils;
import utils.model.AESencryption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet("/GetUserNumbers")
public class GetUserNumbers extends HttpServlet {

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> decodedNumbers = getDecryptedNumbers(req);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/account.jsp");
        req.setAttribute("message", "your last numbers are: ");
        req.setAttribute("decodedNumbers", decodedNumbers);
        dispatcher.forward(req, resp);
    }

    static List<String> getDecryptedNumbers(HttpServletRequest req) throws IOException {
        List<String> decodedNumbers = new ArrayList<>();
        HttpSession httpSession = req.getSession();
        AESencryption aeSencryption = (AESencryption) httpSession.getAttribute("keyPair");
        String password = (String) httpSession.getAttribute("password");
        String fileName = TMP_DIR + "\\" + password.substring(0, 20) + ".txt";

        File myObj = new File(fileName);
        InputStream inputStream = new FileInputStream(myObj);

        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                byte[] byteArr = Base64.getDecoder().decode(line);
                try {
                    String decryptedNumbers = EncryptionUtils.do_AESDecryption(byteArr, aeSencryption.getSymmetricKey(), aeSencryption.getInitializationVector());
                    decodedNumbers.add(decryptedNumbers);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return decodedNumbers;
    }


}
