package com.hacknight.DAO;
import java.sql.*;
import java.util.ArrayList;

import com.hacknight.model.users.UserObj;
import com.hacknight.user.service.UserService;

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
			sql = "insert into users values('"+userObj.getUsername()+"','"+userObj.getEmail()+"','"+userObj.getPassword()+"','"+userObj.getMobno()+"')";
			stmt.executeUpdate(sql);
			
			
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
	
	public static void main(String[] args) {
		UserObj userObj = new UserObj();
		userObj.setEmail("fkljgkljdfjglk@mg");
		userObj.setUsername("d;lkf;lksdkl;fk;sd");
		userObj.setPassword("G123");
		userObj.setMobno("888567756");
		UserService user = new UserService();
		user.createUser(userObj);
	}//end main

}
