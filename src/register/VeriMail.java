package register;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;


public class VeriMail {
	private static final String apiKey= "Your_api";
	public boolean isVaild(String s) {
		boolean validity=false;
        try {
           
            String urlString = "https://emailvalidation.abstractapi.com/v1/?api_key=" + apiKey + "&email=" + java.net.URLEncoder.encode(s, "UTF-8");
            URL url = new URL(urlString);
            
        
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            BufferedReader in;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
           
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject smtpValidObj = jsonResponse.getJSONObject("is_smtp_valid");
            boolean isSmtpValid = smtpValidObj.getBoolean("value");
            
          
            System.out.println("SMTP Check: " + isSmtpValid);
            if(isSmtpValid) {
            	validity=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validity;
	}
	
	public void sendMail(String s,String n) {
		String from = "Your_email";
        
        String password = "Your_pass";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        //props.put("mail.debug", "true"); 

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
            message.setSubject("Your have been registered successfully");
         
            String htmlContent = "<html><body>"
                    + "<h1>Thank You for Registering, " + n + "!</h1>"
                    + "<p>Dear " + n + ",</p>"
                    + "<p>Thank you for registering on our website. We are thrilled to have you with us.</p>"
                    + "<p>We are currently processing your registration and will send you a confirmation email shortly.</p>"
                    + "<p>If you have any questions or need assistance, please feel free to contact us at bibin2434@gmail.com</p>"
                    + "<p>Best regards,<br>SecuringData</p>"
                    + "</body></html>";

       
            message.setContent(htmlContent, "text/html");

            Transport.send(message);
            System.out.println("Success");
        } catch (MessagingException e) {
           
                e.printStackTrace();
            
        }
	}

}
