package com.yychatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.yychat.model.Message;
import com.yychat.model.User;


public class StartServer {
                public static HashMap hmSocket=new HashMap<String,Socket>();
				ServerSocket ss;
				Socket s;
                String userName;
                String passWord;
                Message mess;
                ObjectOutputStream oos;
                
                public StartServer(){                	
                	try{//捕获异常
                		ss=new ServerSocket(3456);
                		System.out.println("服务器已经启动，监听3456端口"); 
                		while(true){
                		s=ss.accept();//接收客户端连接请求
                		System.out.println("连接成功："+s);
                		
                		//接收User对象
                		ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                		User user=(User)ois.readObject();
                		userName=user.getUserName();
                	    passWord=user.getPassWord();	
                		System.out.println(userName);
                		System.out.println(passWord);
                		if(user.getUserMessageType().equals("USER_REGISTER")) {
                			boolean seekUserResult=YychatDbUtil.seekUser(userName);
                			mess=new Message();//验证操作
                			mess.setSender("Server");
                			mess.setReceiver(userName);
                			if(seekUserResult) {
                				mess.setMessageType(Message.message_RegisterFailure);
                			}else {
                				YychatDbUtil.addUser(userName,passWord);
                				mess.setMessageType(Message.message_RegisterSuccess);
                			}
                			sendMessage(s,mess);
                			s.close();
                		}
                		//使用数据库进行用户身份认证
        				if(user.getUserMessageType().equals("USER_LOGIN")) {
                		boolean loginSuccess=YychatDbUtil.loginValidate(userName,passWord);
                		
                		mess=new Message();//验证操作
            			mess.setSender("Server");
            			mess.setReceiver(userName);
                		//if(user.getPassWord().equals("123456")){//对象比较   
            			if(loginSuccess){
                			mess.setMessageType(Message.message_LoginSuccess);//"1"为验证通过
                			String friendString=YychatDbUtil.getFriendString(userName);
                			mess.setContent(friendString);
                			System.out.println(userName+"friend:"+friendString);
                			
                		}else{
                			mess.setMessageType(Message.message_LoginFailure);//"0"为验证不通过                			
                		}
                		//ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                		//oos.writeObject(mess);
                		sendMessage(s,mess);
                		//
                		//if(user.getPassWord().equals("123456")){
                		if(loginSuccess){
                			mess.setMessageType(Message.message_Newpy);
                			mess.setSender("Server");
                			mess.setContent(userName);
                			
                			Set onlineFriendSet=hmSocket.keySet();
                			Iterator it=onlineFriendSet.iterator();
                		    String friendName;
                			while(it.hasNext()){
                				friendName=(String)it.next();
                				mess.setReceiver(friendName);
                				Socket s1=(Socket)hmSocket.get(friendName);
                				sendMessage(s1, mess);
                			}
                			
                			hmSocket.put(userName, s);                		
                		new ServerReceiverThread(s).start();
                	
                		}
                		}
                		}
                	}catch(IOException e){
                		e.printStackTrace();//处理异常
                	}catch (ClassNotFoundException e){
                		e.printStackTrace();
                	} 
                	
                }
                private void sendMessage(Socket s,Message mess) throws IOException {			
        			oos=new ObjectOutputStream(s.getOutputStream());
                	 oos.writeObject(mess);
        		}
}
