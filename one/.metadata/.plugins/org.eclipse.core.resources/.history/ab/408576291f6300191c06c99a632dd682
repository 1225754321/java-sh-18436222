package com.yychatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.yychat.model.Message;

public class ServerReceiverThread extends Thread{//代码复用
	Socket s;
	
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Message mess;
	String sender;
	
	public ServerReceiverThread(Socket s){//s是与发送者相对应的服务器Socket对象
		this.s=s;
	}
	
	public void run(){
		
		while (true) {
			try {
				ois = new ObjectInputStream(s.getInputStream());
				mess=(Message)ois.readObject();//接收聊天信息，阻塞
				sender=mess.getSender();
				System.out.println(mess.getSender()+"对"+mess.getReceiver()+"说："+mess.getContent());
				
				if(mess.getMessageType().equals(Message.message_Common)){
					Socket s1=(Socket)StartServer.hmSocket.get(mess.getReceiver());//得到了与接收者相对应的服务器Socket对象
					sendMessage(s1,mess);
				}
				
				//第2步：服务器接收到该请求后发送在线好友信息(类型：message_OnlineFriend)
				if(mess.getMessageType().equals(Message.message_RequestOnlineFriend)){
					Set friendSet=StartServer.hmSocket.keySet();
					java.util.Iterator it =friendSet.iterator();
					String friendName;
					String friendString=" ";
					while(it.hasNext()){
						friendName=(String)it.next();
						if(!friendName.equals(mess.getSender()))
							friendString=friendName+" "+friendString;
							
					}
					System.out.println("全部好友的名字："+friendString);
					
					mess.setContent(friendString);
					mess.setMessageType(Message.message_OnlineFriend);
					mess.setSender("Server");
					mess.setReceiver("sender");
					sendMessage(s,mess);
				}
				
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

	private void sendMessage(Socket s,Message mess) throws IOException {
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(mess);//转发聊天信息
	}
}
