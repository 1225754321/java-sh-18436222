package com.qqchatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.qqchat.model.User;

public class StartServer {
	ServerSocket ss;
	public StartServer() {
		try {
			ss=new ServerSocket(3456);
			System.out.println("服务器已经启动，监听3456端口");
			Socket s=ss.accept();//接受客户端连接请求
			System.out.println("连接成功："+s);
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			User user=(User)ois.readObject();
			System.out.println(user.getUserName());
			System.out.println(user.getPassWord());
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
