package com.yychatclient.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import com.yychat.model.Message;
import com.yychat.model.User;

public class ClientConnect {
	     public Socket s;//静态成员，类变量
	     public static HashMap hmSocket=new HashMap<String,Socket>();
          public ClientConnect()
          {
        	try {
				s=new Socket("127.0.0.1",3456);//服务器地址，本地地址
			    } catch (IOException e) 
        	       {
				  // TODO Auto-generated catch block
				   e.printStackTrace();
			       }
          }        	
           public boolean loginValidate(User user){
        	   //输入输出流对象,发送对象
        	   //字节输出流对象包装，对象输出流对象
        	   boolean loginsuccess=false;
        	   ObjectOutputStream oos;
        	   ObjectInputStream ois;
        	   Message mess=null;
			try{
        	   oos=new ObjectOutputStream(s.getOutputStream());
        	   oos.writeObject(user);
        	   
        	   ois=new ObjectInputStream(s.getInputStream());
        	   mess=(Message)ois.readObject();
        	   
        	   if(mess.getMessageType().equals(Message.message_LoginSuccess)){
        		   loginsuccess=true;
        		   System.out.println(user.getUserName()+" 登录成功！！！");
        		   hmSocket.put(user.getUserName(),s);
        		   new ClientReceiverThread(s).start();
        	   }
        	   
        	   }catch (IOException | ClassNotFoundException e){
        		   e.printStackTrace();
			}
        	   return loginsuccess;
           }
          }

