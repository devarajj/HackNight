package com.hacknight.actions;

import com.hacknight.model.users.UserObj;
import com.hacknight.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class SignUpAction  extends ActionSupport{
	/* struts param  */
	private String email;
	private String username;
	private String password;
	private String confirmpassword;
	private String mobno;
	
	/* param getters  and setters  */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getMobno() {
		return mobno;
	}
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	
	public String execute(){
		
		System.out.println("email"+email+"\nusername:"+username+"\npassword:"+password+"\nconfirm password:"+confirmpassword+"\nmobno"+mobno);
		UserService userService =new UserService();
		UserObj userObj = new UserObj();
		userObj.setEmail(email);
		userObj.setUsername(username);
		userObj.setMobno(mobno);
		userObj.setPassword(password);
		userObj.setStatus("I");
		userService.createUser(userObj);
		return SUCCESS;
	}
	

}
