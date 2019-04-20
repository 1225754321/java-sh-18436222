package com.yychatclient.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.yychat.model.Message;
import com.yychatclient.view.ClientLogin;
import com.yychatclient.view.FriendChat1;
import com.yychatclient.view.FriendList;

public class ClientReceiverThread extends Thread{

	private Socket s;
	
	public ClientReceiverThread(Socket s){
		this.s=s;
	}
	public void run(){
		ObjectInputStream ois;
		while(true){
			try {
				ois = new ObjectInputStream(s.getInputStream());
				Message mess=(Message)ois.readObject();//接收聊天信息,线程阻塞
				String showMessage=mess.getSender()+"对"+mess.getReceiver()+"说："+mess.getContent();
				System.out.println(showMessage);
				//jta.append(showMessage+"\r\n");
				//在好友界面FriendChat1上显示聊天信息
				//1、如何拿到好友聊天界面，思路：保存好友聊天界面，
				FriendChat1 friendChat1=(FriendChat1)FriendList.hmFriendChat1.get(mess.getReceiver()+"to"+mess.getSender());//还是接收者+发送者吗？
								
				//2、再显示信息
				friendChat1.appendJta(showMessage);
				
				//第3步：客户端接收服务器发送来的在线好友信息，然后利用该信息激活在线好友的图标
				if(mess.getMessageType().equals(Message.message_OnlineFriend)){
					System.out.println("在线好友"+mess.getContent());
					
					FriendList friendList=(FriendList)ClientLogin.hmFriendlist.get(mess.getReceiver());
					friendList.setEnableFriendIcon(mess.getContent());
				}
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}	
		}
	}
}
