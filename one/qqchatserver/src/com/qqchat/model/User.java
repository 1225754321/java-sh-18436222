package com.qqchat.model;

import java.io.Serializable;

public class User implements Serializable{
	private String userName;
	private String passWord;
	
	
	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserName() {
		return userName;
		
	}
	
	
}
