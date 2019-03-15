package com.qqchatclient.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

import com.qqchat.model.User;

public class ClientConnect {
	Socket s;
	public ClientConnect() {
				try {
					s= new Socket("127.0.0.1",3456);//±¾»úµØÖ·
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	public void loginValidate(User user) {
		ObjectOutputStream oos;
		try {
			oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}