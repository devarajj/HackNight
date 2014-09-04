package com.hacknight.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hacknight.constants.UserConstants;
import com.hacknight.model.users.UserObj;
import com.hacknight.user.service.UserService;
import com.hacknight.util.KeyGenerator;

public class UserDao {



	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/dev";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "root";


	public static Connection connection(){
		Connection conn = null;
		Statement stmt = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);


		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}



	public ArrayList<String> getUser(){
		Statement stmt = null;
		ArrayList<String> users = new ArrayList<String>();
		Connection connection = UserDao.connection();
		// Execute a query
		System.out.println("Creating statement...");
		try {
			String sql; 
			stmt = connection.createStatement();
			sql = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(sql);
			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by user name
				users.add(rs.getString("email"));
				System.out.println( rs.getString("email"));   
				//System.out.println(rs.getString("password"));

			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}
	
	
	
	
	public void insertUser(UserObj userObj){
		Connection connection = UserDao.connection();
		Statement stmt = null;
		String sql; 
		try {
			stmt = connection.createStatement();
			
			System.out.println("Inserting records into the table...");
			sql = "insert into users(username,email,password,mobno,status) values('"+userObj.getUsername()+"','"+userObj.getEmail()+"','"+userObj.getPassword()+"','"+userObj.getMobno()+"','"+userObj.getStatus()+"')";
			stmt.executeUpdate(sql);
			System.out.println("user inserted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	
	/***
	 * To send activation mail email to user
	 * @param toEmail
	 */

	public void sendActivationMail(String toEmail,String subject, String emailContent) {
 
		final String username = "devaraj9031@cse.ssn.edu.in";
		final String password = "devaraj";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setContent(emailContent,"text/html");
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			System.out.println("Mail not found");
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	public static void main(String[] args) {
/*		UserObj userObj = new UserObj();
		userObj.setEmail("devarajcsessn@gmail.com");
		userObj.setUsername("King of heavens");
		userObj.setPassword("G123");
		userObj.setMobno("888567756");
		userObj.setStatus("I");
		UserService user = new UserService();
		user.createUser(userObj);*/
		UserDao userDao =  new UserDao();
		String token = KeyGenerator.symmetricEncrypt("devarajcsessn@gmail.com", UserConstants.SECRET_KEY);
		String activationLink = "localhost:8081/AccountActivation?token="+token;
		System.out.println(token);
		
		String message = "Hi<br/>"
				+ "Thank you for registering your account.<br/>"
				+ "To finally activate your account please click the following link.<br/>"
				+"'<a href='"+activationLink+"'>'"+activationLink+"'</a>'"
				+ "If clicking the link doesn't work you can copy the link into your browser window and paste it there directly.<br/>"
				+ "Thanks<br/>"
				+ "Team";
		
		
		userDao.sendActivationMail("devarajcsessn@gmail.com","Activation Link",message);
		
	}//end main

}
