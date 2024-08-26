package register;

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
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.sql.*;
import java.util.Base64;

@WebServlet("/Reginfo")
public class Reginfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    private static final String ALGORITHM = "AES";
    private static SecretKey secretKey;
    private static String KEY="";
    @Override
    public void init() throws ServletException {
        try {
            
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(192); 
            secretKey = keyGenerator.generateKey();
            secretKeyToString(secretKey);
        } catch (Exception e) {
           
        	e.printStackTrace();
        }
    }
    public static void secretKeyToString(SecretKey key) {
        byte[] keyBytes = key.getEncoded();
        KEY=Base64.getEncoder().encodeToString(keyBytes);
    }

  
    private String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

  
    public static String CapName(String s){
        String ans="";
        s=s.toLowerCase();
        String names[]=s.split(" ");
        for(String name:names){
            if(name.length()>0){
                char c=(char)(name.charAt(0)-32);
                String word=c+name.substring(1,name.length());
                ans+=word+" ";
            }
        }
        
        return ans;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = CapName(request.getParameter("name"));
        VeriMail vm=new VeriMail();
        String mail = request.getParameter("email");
        String pass = request.getParameter("password");
        PrintWriter pw = response.getWriter();
        if(vm.isVaild(mail)) {
        try {
            String encryptedPass = encrypt(pass);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root", "root");
            String sql = "insert into info(name, email, password,key_val) values(?, ?, ?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, mail);
            st.setString(3, encryptedPass);
            st.setString(4, KEY);
            vm.sendMail(mail, name);
            int row = st.executeUpdate();
            if (row > 0) {
            	
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
                dispatcher.include(request, response);
                
            } else {
                pw.print("<html><body><script>alert('Invalid submission')</script></body></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pw.print("<html><body><script>alert('Error occurred')</script></body></html>");
        }
        }else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("register.html");
            dispatcher.include(request, response);
        	 pw.print("<html><body><script>alert('Invalid email')</script></body></html>");
        }
    }
}
