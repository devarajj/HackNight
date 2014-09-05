package com.hacknight.user.service;

import java.util.ArrayList;

import org.apache.tomcat.util.buf.UDecoder;

import com.hacknight.DAO.UserDao;
import com.hacknight.model.users.UserObj;

public class UserService {
	UserDao mysqlDAO;
	
	public UserService(){
		mysqlDAO = new UserDao();
	}

	
	/**
	 * To check whether user exists in database
	 * @param email
	 * @return {@link Boolean}
	 */
	public boolean isUserExist(String email){
		boolean isexist = false;
		ArrayList<String> users = mysqlDAO.getUser();
		System.out.println(users);
		//check email is exist in list
		isexist= users.contains(email);
		return isexist;
	}
	/**
	 * To create new user account
	 * @param userObj
	 * @author dev
	 */
	public void createUser(UserObj userObj){
		UserService userService = new UserService();
		boolean result	= userService.isUserExist(userObj.getEmail());
		if(! result){
			System.out.println("not exists");
			mysqlDAO.insertUser(userObj);
		}else{
			System.out.println("aleady exists");
		}
	}

	


}
