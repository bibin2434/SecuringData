package login;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Base64;

@WebServlet("/LogValid")
public class LogValid extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String ALGORITHM = "AES";
    private static SecretKey secretKey;

//    @Override
//    public void init() throws ServletException {
//        try {
//            // Initialize AES key from a secure location
//            // For demonstration, we'll generate a new key each time
//            // In practice, load this key from a secure storage
//            secretKey = generateKey();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Generate a new AES key
//    private SecretKey generateKey() throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
//        keyGenerator.init(128); // Key size: 128, 192, or 256 bits
//        return keyGenerator.generateKey();
//    }

 
    private SecretKey stringToSecretKey(String keyString) {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

   

    
    private String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PrintWriter pw = response.getWriter();

        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root", "root");
            String sql = "select password, key_val,name from info where email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String encryptedPass = rs.getString("password");
                String keyFromDb = rs.getString("key_val");
                String name=rs.getString("name");

                secretKey = stringToSecretKey(keyFromDb);

               
                String decryptedPass = decrypt(encryptedPass);

               
                if (password.equals(decryptedPass)) {
                	 HttpSession session = request.getSession();
                	 
                	session.setAttribute("user", name);//welcome.jsp success.jsp
                	 response.sendRedirect("success.jsp?name=" + name); 
                    
                } else {
                	
                    pw.print("<html><body><script>alert('Invalid email or password')</script></body></html>");
                   
                }
            } else {
                pw.print("<html><body><script>alert('User not found')</script></body></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pw.print("<html><body><script>alert('Error occurred')</script></body></html>");
        }
        
        
    }
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Handle GET request
//        String user = (String) request.getAttribute("user");
//        
//        request.setAttribute("user", user);
//        request.getRequestDispatcher("welcome.jsp").forward(request, response);
//    }
    
}
