package upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UpUser")
@MultipartConfig
public class UpUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Directory where uploaded files will be saved
    
    private static String UPLOAD_DIR = "C:\\Users\\ELCOT\\Desktop\\DB\\";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 HttpSession session = request.getSession(false);
         if (session == null || session.getAttribute("user") == null) {
             response.sendRedirect("login.html");
             return;
         }
         String user = (String) session.getAttribute("user");
         String dirMix = UPLOAD_DIR + user;
    	String dir=dirMix.replaceAll("\\s","")+"\\";
        // Create the upload directory if it does not exist
    	
        File uploadDir = new File(dir);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Get the file part from the request
        Part filePart = request.getPart("file");

        // Extract file name from content-disposition header
        String fileName = extractFileName(filePart);

        System.out.println(dirMix);
        System.out.println(dir);
        
        File file = new File(dir + fileName);

        // Save the file to the server
        try (InputStream inputStream = filePart.getInputStream();
             OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        // Get file details
        String filePath = file.getAbsolutePath();
        long fileSize = file.length();
        String fileType = filePart.getContentType();
        Date uploadDate = new Date();

        // Provide feedback to the user
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>File uploaded successfully!</h2>");
        response.getWriter().println("<p>File Name: " + fileName + "</p>");
        response.getWriter().println("<p>File Path: " + filePath + "</p>");
        response.getWriter().println("<p>File Size: " + fileSize + " bytes</p>");
        response.getWriter().println("<p>File Type: " + fileType + "</p>");
        response.getWriter().println("<p>Upload Date: " + uploadDate + "</p>");
        response.getWriter().println("</body></html>");
    }

    // Method to extract the file name from the content-disposition header
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                String fileName = item.substring(item.indexOf('=') + 1).trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }
}
