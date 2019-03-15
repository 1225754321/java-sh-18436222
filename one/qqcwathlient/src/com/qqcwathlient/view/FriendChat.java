package com.qqcwathlient.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FriendChat extends JFrame implements ActionListener{
	
	
	//zhongbu
	JScrollPane jsp;
	JTextArea jta;
	
	
	
	//nanbu
	JPanel jp;
	JTextField jtx;
	JButton jb;
	
	
	public FriendChat(String sender,String receiver) {
		
		//zhongbu
		jta=new JTextArea();
		jta.setForeground(Color.red);
		jta.setEditable(false);
		jsp=new JScrollPane(jta);
		this.add(jsp);
		
		//nanbu
		jp=new JPanel();
		jb=new JButton("发送");
		jb.addActionListener(this);
		jtx=new JTextField(15);
		jtx.setForeground(Color.blue);
		jp.add(jtx);jp.add(jb);
		this.add(jp,"South");
		
		//jiemianshezhi
		this.setSize(350,240);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(sender+"和"+receiver+"正在聊天");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		//FriendChat friendChat=new FriendChat();

	}

	@Override
	public void actionPerformed(ActionEvent args) {
		if(args.getSource()==jb) jta.append(jtx.getText()+"\r\n");
		
	}

}
