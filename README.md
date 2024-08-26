# SecuringData - Registration and Login System

## Overview

SecuringData is a Java-based web application that provides a secure registration and login system. The application uses AES encryption to securely store user passwords in a MySQL database and validates email addresses using an external API. Additionally, the system includes functionality to send a confirmation email upon successful registration.

## Features

- **Secure Registration**: Users can register with their name, email, and password. The password is encrypted using AES encryption before storing it in the database.
- **Email Validation**: The system validates the email address using the Abstract API to ensure it is a valid and reachable email address.
- **Secure Login**: Users can log in with their registered email and password. The password is decrypted using the stored AES key, and authentication is performed.
- **Email Confirmation**: Upon successful registration, a confirmation email is sent to the user.

## Technology Stack

- **Java**: Core programming language used for the backend.
- **JSP/Servlets**: For handling HTTP requests and responses.
- **AES Encryption**: For secure storage of user passwords.
- **MySQL**: Database used for storing user information.
- **Abstract API**: Used for validating email addresses.
- **JavaMail API**: For sending confirmation emails to users.
- **HTML/CSS**: For the front-end interface.

## Project Structure

```
/src
├── register
│   ├── Reginfo.java          # Servlet for handling registration
│   ├── VeriMail.java         # Class for email validation and sending confirmation emails
├── login
│   ├── LogValid.java         # Servlet for handling login
/web
├── register.html             # Registration page
├── login.html                # Login page
├── success.jsp               # Success page after login
├── error.jsp                 # Error page
```

## Setup and Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/bibin2434/SecuringData.git
   ```
2. **Import the Project**:
   - Import the project into your favorite IDE (Eclipse, IntelliJ, etc.).

3. **Set Up MySQL Database**:
   - Create a MySQL database named `register`.
   - Create the `info` table using the following SQL script:
     ```sql
     CREATE TABLE info (
         id INT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(255),
         email VARCHAR(255),
         password VARCHAR(255),
         key_val VARCHAR(255)
     );
     ```

4. **Update Database Configuration**:
   - Update the database URL, username, and password in the `Reginfo.java` and `LogValid.java` files to match your MySQL setup.

5. **Run the Application**:
   - Deploy the application on a servlet container (e.g., Apache Tomcat) and access it via a web browser.

## Usage

- **Registration**: Navigate to `/register.html`, fill in the details, and submit. A confirmation email will be sent to the registered email address.
- **Login**: Navigate to `/login.html`, enter your credentials, and log in. If successful, you will be redirected to the success page.

## Security Considerations

- **Encryption**: The application uses AES encryption to ensure that passwords are stored securely in the database.
- **Email Validation**: The system uses an external API to validate email addresses before allowing registration, ensuring only valid and reachable emails are accepted.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss any changes.
