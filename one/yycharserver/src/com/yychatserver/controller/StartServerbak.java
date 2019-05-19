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


public class StartServerbak {
                public static HashMap hmSocket=new HashMap<String,Socket>();
				ServerSocket ss;
				Socket s;
                String userName;
                String passWord;
                Message mess;
                ObjectOutputStream oos;
                
                public StartServerbak(){                	
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
                		//使用数据库进行用户身份认证
        				//1、加载驱动程序
        				Class.forName("com.mysql.jdbc.Driver");
        				System.out.println("已经加载了数据库驱动！");
        				//2、连接数据库
        				String url="jdbc:mysql://127.0.0.1:3306/yychat";
        				System.out.println(url);
        				//中文用户名必须用下面的url
        				//String url="jdbc:mysql://127.0.0.1:3306/yychat?useUnicode=true&characterEncoding=UTF-8";
        				String dbUser="root";
        				String dbPass="";				
        				Connection conn=DriverManager.getConnection(url,dbUser,dbPass);
        				
        				//3、创建PreparedStatement对象，用来执行SQL语句
        				String user_Login_Sql="select * from user where username=? and password=?";
        				PreparedStatement ptmt=conn.prepareStatement(user_Login_Sql);
        				ptmt.setString(1, userName);
        				ptmt.setString(2, passWord);
        				
        				//4、执行查询，返回结果集
        				ResultSet rs=ptmt.executeQuery();
        				
        				//5、根据结果集来判断是否能登录
        				boolean loginSuccess=rs.next();
                		//实现密码验证功能
						

    //6、在yychatserver项目中添加数据库驱动：Build Path->Add External JARs->mysql-connector-java-5.1.6-bin
		//创建不了				
                		mess=new Message();//验证操作
            			mess.setSender("Server");
            			mess.setReceiver(userName);
                		//if(user.getPassWord().equals("123456")){//对象比较   
            			if(loginSuccess){
                			mess.setMessageType(Message.message_LoginSuccess);//"1"为验证通过
                			String friend_Relation_Sql="select slaveuser from relation where majoruser=? and relationtype=1";
                			ptmt=conn.prepareStatement(friend_Relation_Sql);
                			ptmt.setString(1,userName);
                			rs=ptmt.executeQuery();
                			String friendString="";
                			while(rs.next()) {
                				
                				friendString=friendString+rs.getString("slaveuser")+" ";
                			}
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
                	}catch(IOException e){
                		e.printStackTrace();//处理异常
                	}catch (ClassNotFoundException e){
                		e.printStackTrace();
                	} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }
                private void sendMessage(Socket s,Message mess) throws IOException {			
        			oos=new ObjectOutputStream(s.getOutputStream());
                	 oos.writeObject(mess);
        		}
}
